// ClientSocket.cpp : ���� �����Դϴ�.
//

#include "stdafx.h"
#include "ScreenSender.h"
#include "ClientSocket.h"

#include "Defines.h"

// CClientSocket

CClientSocket::CClientSocket()
{
}

CClientSocket::~CClientSocket()
{
}


// CClientSocket ��� �Լ�

// ��Ŷ ���Ž�
void CClientSocket::OnReceive(int nErrorCode)
{
	if (nErrorCode == 0)	// ��Ŷ�� ���� ���� ��
	{
		// ��Ŷ�� �켱 ����
		int packet = -1;
		this->Receive(&packet, 4);

		if (packet == PACKET_FILE_START)	// ���� �۽� ���� ��Ŷ ����
		{
			// ������ ��� ����
			int send_packet = PACKET_FILE_START;
			this->Send(&send_packet, 4);

			// ���ϸ� �غ�
			CString sFileName = this->m_sFileName + ".jpg";
			CString sFilePath = this->m_sPath + "\\" + sFileName;
			//unsigned int nFileNameLength = sFileName.GetLength() + 1;
			unsigned int nFileNameLength = sFilePath.GetLength() + 1;

			// ���� ����
			CFile oFile;
			unsigned int nFileDataLength = 0;
			if (!oFile.Open(sFilePath, CFile::modeRead | CFile::typeBinary))
			{
				// ���� ���� �۽�
				int packet_tail = PACKET_TAIL;
				this->Send(&packet_tail, 4);

				return;
			}

			// ������ ���� ���
			nFileDataLength = (unsigned int)oFile.GetLength();

			// �����̸� ���� ������
			this->Send(&nFileNameLength, 4);

			// �����̸� ������
			//char* bufFileName = LPSTR(LPCTSTR(sFileName));
			char* bufFileName = LPSTR(LPCTSTR(sFilePath));
			this->Send(bufFileName, nFileNameLength);

			// ���� ���� ������
			this->Send(&nFileDataLength, 4);

			// ���� �о ���� ������
			unsigned int nSize = nFileDataLength;
			unsigned int nReaded = 0, nSendable = 0, nSended = 0, nPos;
			char readBuff[PACKET_BUFF_MAX];

			while (nSize > 0)
			{	
				nReaded = oFile.Read(readBuff, (nSize > PACKET_BUFF_MAX ? PACKET_BUFF_MAX : nSize));
				nSendable = nReaded;
				nPos = 0;

				// ���� ���� �ٺ���
				while (nSendable > 0)
				{
					nSended = this->Send(readBuff + nPos, nSendable);

					nSendable -= nSended;
					nPos += nSended;
				}

				nSize -= nReaded;
			}

			oFile.Close();

			// ���� ���� �۽�
			int packet_tail = PACKET_TAIL;
			this->Send(&packet_tail, 4);
		}
		else if (packet == PACKET_OK)	
		{
			OutputDebugString("OK");
		}
	}

	CSocket::OnReceive(nErrorCode);
}

// ����
void CClientSocket::OnClose(int nErrorCode)
{
	// ���� ����� 
	this->m_pWnd->SendMessage(WM_SOCKET_CLOSED);

	CSocket::OnClose(nErrorCode);
}
