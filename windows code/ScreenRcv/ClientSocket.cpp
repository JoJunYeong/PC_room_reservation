// ClientSocket.cpp : 구현 파일입니다.
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


// CClientSocket 멤버 함수

void CClientSocket::OnReceive(int nErrorCode)
{
	if (nErrorCode == 0) 
	{
		// 패킷을 우선 받음
		int packet = -1;
		this->Receive(&packet, 4);

		// 기본 패킷 분석
		if (packet == PACKET_HEAD)				// 헤더	패킷 수신
		{
			int send_packet = PACKET_FILE_START;
			this->Send(&send_packet, 4);
		}
		else if (packet == PACKET_FILE_START)	// 파일 송신 시작 패킷 수신
		{
			// 파일 정보를 수신하여 파일을 저장 후 완료

			// 파일이름 길이 수신
			unsigned int nFileNameLength = -1;
			this->Receive(&nFileNameLength, 4);

			// 파일 읽기 오류인지 확인
			if (nFileNameLength == PACKET_TAIL)
			{			
				CString sPeerName;
				UINT nPeerPort = -1;
				if (this->GetPeerName(sPeerName, nPeerPort))
				{	
					return;
				}
			}

			// 파일 이름 경로을 수신
			CString sFileName = "";
			char *buffFileName = new char[nFileNameLength];
			ZeroMemory(buffFileName, nFileNameLength);
			this->Receive(buffFileName, nFileNameLength);

			sFileName.Format("%s", buffFileName);
			
			// 디렉토리 생성확인
			{
			CString strDir = sFileName.Left(sFileName.ReverseFind('\\'));
				
				// 파일 경로 생성
				CreateDirectory(strDir, NULL);
			}


			// 크리티컬 섹션 처리
			this->m_pCritalSection->Lock();

			// 파일을 생성하고 
			CFile oFile;
			if (oFile.Open(sFileName, CFile::modeCreate | CFile::modeWrite | CFile::typeBinary))
			{
				// 파일의 데이터 길이를 받는다.
				unsigned int nFileDataLength = -1;
				this->Receive(&nFileDataLength, 4);

				// 파일 데이터 수신 및 저장
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

			// 꼬리 수신
			int packet_end = -1;
			this->Receive(&packet_end, 4);

			if (packet_end == PACKET_TAIL)
			{
				// 파일 테일 송신
				int packet_ok = PACKET_OK;
				this->Send(&packet_ok, 4);
			}

			delete buffFileName;

			// 메시지			
			CString msg;
			CTime curtime = CTime::GetCurrentTime();			
			msg.Format("파일 수신됨 (%02d:%02d:%02d)", curtime.GetHour(), curtime.GetMinute(), curtime.GetSecond());
			::SendMessage(this->m_hWnd, WM_UPDATE_LOG, (WPARAM)&msg, 0);
		}
	}

	CSocket::OnReceive(nErrorCode);
}


void CClientSocket::OnClose(int nErrorCode)
{
	// 메시지
	CString sPeerName;
	UINT nPeerPort = -1;
	if (this->GetPeerName(sPeerName, nPeerPort))
	{	
		// 메시지			
		CString msg;
		CTime curtime = CTime::GetCurrentTime();			
		msg.Format("클라이언트(%s) 종료됨. (%02d:%02d:%02d)", sPeerName.GetBuffer(), curtime.GetHour(), curtime.GetMinute(), curtime.GetSecond());
		::SendMessage(this->m_hWnd, WM_UPDATE_LOG, (WPARAM)&msg, 0);
	}

	// 소켓 종료 알림
	if (!m_bForceClear)
		::SendMessage(this->m_hWnd, WM_CLIENT_CLOSED, 0, 0);

	CSocket::OnClose(nErrorCode);
}
