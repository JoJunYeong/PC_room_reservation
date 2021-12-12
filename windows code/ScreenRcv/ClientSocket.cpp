// ClientSocket.cpp : ���� �����Դϴ�.
//

#include "stdafx.h"
#include "ScreenRcv.h"
#include "ClientSocket.h"

#include "Defines.h"

// CClientSocket

CClientSocket::CClientSocket()
{
	m_bForceClear = FALSE;
}

CClientSocket::~CClientSocket()
{
}


// CClientSocket ��� �Լ�

void CClientSocket::OnReceive(int nErrorCode)
{
	if (nErrorCode == 0) 
	{
		// ��Ŷ�� �켱 ����
		int packet = -1;
		this->Receive(&packet, 4);

		// �⺻ ��Ŷ �м�
		if (packet == PACKET_HEAD)				// ���	��Ŷ ����
		{
			int send_packet = PACKET_FILE_START;
			this->Send(&send_packet, 4);
		}
		else if (packet == PACKET_FILE_START)	// ���� �۽� ���� ��Ŷ ����
		{
			// ���� ������ �����Ͽ� ������ ���� �� �Ϸ�

			// �����̸� ���� ����
			unsigned int nFileNameLength = -1;
			this->Receive(&nFileNameLength, 4);

			// ���� �б� �������� Ȯ��
			if (nFileNameLength == PACKET_TAIL)
			{			
				CString sPeerName;
				UINT nPeerPort = -1;
				if (this->GetPeerName(sPeerName, nPeerPort))
				{	
					return;
				}
			}

			// ���� �̸� ����� ����
			CString sFileName = "";
			char *buffFileName = new char[nFileNameLength];
			ZeroMemory(buffFileName, nFileNameLength);
			this->Receive(buffFileName, nFileNameLength);

			sFileName.Format("%s", buffFileName);
			
			// ���丮 ����Ȯ��
			{
			CString strDir = sFileName.Left(sFileName.ReverseFind('\\'));
				
				// ���� ��� ����
				CreateDirectory(strDir, NULL);
			}


			// ũ��Ƽ�� ���� ó��
			this->m_pCritalSection->Lock();

			// ������ �����ϰ� 
			CFile oFile;
			if (oFile.Open(sFileName, CFile::modeCreate | CFile::modeWrite | CFile::typeBinary))
			{
				// ������ ������ ���̸� �޴´�.
				unsigned int nFileDataLength = -1;
				this->Receive(&nFileDataLength, 4);

				// ���� ������ ���� �� ����
				if (nFileDataLength > 0) 
				{
					unsigned int nSize = nFileDataLength;
					unsigned int nWrited = 0, nReceived = 0;
					char recvBuff[PACKET_BUFF_MAX];

					while (nSize > 0)
					{
						nReceived = this->Receive(recvBuff, (nSize > PACKET_BUFF_MAX ? PACKET_BUFF_MAX : nSize));

						oFile.Write(recvBuff, nReceived);

						nSize -= nReceived;
					}
				}

				oFile.Close();
			}

			this->m_pCritalSection->Unlock();

			// ���� ����
			int packet_end = -1;
			this->Receive(&packet_end, 4);

			if (packet_end == PACKET_TAIL)
			{
				// ���� ���� �۽�
				int packet_ok = PACKET_OK;
				this->Send(&packet_ok, 4);
			}

			delete buffFileName;

			// �޽���			
			CString msg;
			CTime curtime = CTime::GetCurrentTime();			
			msg.Format("���� ���ŵ� (%02d:%02d:%02d)", curtime.GetHour(), curtime.GetMinute(), curtime.GetSecond());
			::SendMessage(this->m_hWnd, WM_UPDATE_LOG, (WPARAM)&msg, 0);
		}
	}

	CSocket::OnReceive(nErrorCode);
}


void CClientSocket::OnClose(int nErrorCode)
{
	// �޽���
	CString sPeerName;
	UINT nPeerPort = -1;
	if (this->GetPeerName(sPeerName, nPeerPort))
	{	
		// �޽���			
		CString msg;
		CTime curtime = CTime::GetCurrentTime();			
		msg.Format("Ŭ���̾�Ʈ(%s) �����. (%02d:%02d:%02d)", sPeerName.GetBuffer(), curtime.GetHour(), curtime.GetMinute(), curtime.GetSecond());
		::SendMessage(this->m_hWnd, WM_UPDATE_LOG, (WPARAM)&msg, 0);
	}

	// ���� ���� �˸�
	if (!m_bForceClear)
		::SendMessage(this->m_hWnd, WM_CLIENT_CLOSED, 0, 0);

	CSocket::OnClose(nErrorCode);
}
