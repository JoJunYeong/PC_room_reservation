// ClientSocket.cpp : 구현 파일입니다.
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


// CClientSocket 멤버 함수

// 패킷 수신시
void CClientSocket::OnReceive(int nErrorCode)
{
	if (nErrorCode == 0)	// 패킷이 정상 수신 시
	{
		// 패킷을 우선 받음
		int packet = -1;
		this->Receive(&packet, 4);

		if (packet == PACKET_FILE_START)	// 파일 송신 시작 패킷 수신
		{
			// 파일을 열어서 전송
			int send_packet = PACKET_FILE_START;
			this->Send(&send_packet, 4);

			// 파일명 준비
			CString sFileName = this->m_sFileName + ".jpg";
			CString sFilePath = this->m_sPath + "\\" + sFileName;
			//unsigned int nFileNameLength = sFileName.GetLength() + 1;
			unsigned int nFileNameLength = sFilePath.GetLength() + 1;

			// 파일 열기
			CFile oFile;
			unsigned int nFileDataLength = 0;
			if (!oFile.Open(sFilePath, CFile::modeRead | CFile::typeBinary))
			{
				// 파일 테일 송신
				int packet_tail = PACKET_TAIL;
				this->Send(&packet_tail, 4);

				return;
			}

			// 파일의 길이 얻기
			nFileDataLength = (unsigned int)oFile.GetLength();

			// 파일이름 길이 보내기
			this->Send(&nFileNameLength, 4);

			// 파일이름 보내기
			//char* bufFileName = LPSTR(LPCTSTR(sFileName));
			char* bufFileName = LPSTR(LPCTSTR(sFilePath));
			this->Send(bufFileName, nFileNameLength);

			// 파일 길이 보내기
			this->Send(&nFileDataLength, 4);

			// 파일 읽어서 파일 보내기
			unsigned int nSize = nFileDataLength;
			unsigned int nReaded = 0, nSendable = 0, nSended = 0, nPos;
			char readBuff[PACKET_BUFF_MAX];

			while (nSize > 0)
			{	
				nReaded = oFile.Read(readBuff, (nSize > PACKET_BUFF_MAX ? PACKET_BUFF_MAX : nSize));
				nSendable = nReaded;
				nPos = 0;

				// 읽은 것을 다보냄
				while (nSendable > 0)
				{
					nSended = this->Send(readBuff + nPos, nSendable);

					nSendable -= nSended;
					nPos += nSended;
				}

				nSize -= nReaded;
			}

			oFile.Close();

			// 파일 테일 송신
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

// 종료
void CClientSocket::OnClose(int nErrorCode)
{
	// 소켓 종료시 
	this->m_pWnd->SendMessage(WM_SOCKET_CLOSED);

	CSocket::OnClose(nErrorCode);
}
