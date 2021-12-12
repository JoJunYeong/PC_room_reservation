// ServerSocket.cpp : 구현 파일입니다.
//

#include "stdafx.h"
#include "ScreenRcv.h"
#include "ServerSocket.h"

#include "Defines.h"
#include "SocketThread.h"
#include "ClientSocket.h"
#include "ScreenRcvDlg.h"

// CServerSocket

CServerSocket::CServerSocket()
{
	m_thClient = NULL;
}

CServerSocket::~CServerSocket()
{
}


// CServerSocket 멤버 함수

// 접속 수신시
void CServerSocket::OnAccept(int nErrorCode)
{
	// 정상 수신시
	if (nErrorCode == 0) 
	{
		// 접속된 소켓을 일단 받고
		CSocket sock;
		Accept(sock);

		// 클라이언트용 스레드를 생성
		CSocketThread *pThread = (CSocketThread *)AfxBeginThread(RUNTIME_CLASS(CSocketThread), THREAD_PRIORITY_NORMAL, 0, CREATE_SUSPENDED);
		if (pThread == NULL)
		{
			sock.Close();
			return;
		}

		// 스레드 및 클라이언트 소켓 초기화
		CScreenRcvDlg *pDlg = (CScreenRcvDlg *) this->m_pWnd;
		if (pDlg != NULL) 
		{
			// 로그 메시지 처리
			CString sPeerName;
			UINT nPeerPort = -1;
			if (sock.GetPeerName(sPeerName, nPeerPort))
			{
				CString msg = "클라이언트(" + sPeerName + ") 접속됨.";
				pDlg->SendMessage(WM_UPDATE_LOG, (WPARAM)&msg, 0);
			}

			// 메인 윈도우 설정들 넘김
			pThread->m_oClientSocket.m_hWnd = pDlg->GetSafeHwnd();
			pThread->m_oClientSocket.m_pCritalSection = &pDlg->m_oCriticalSection;
		}

		// 소켓 분리 및 스레드 정상 동작 처리
		pThread->m_hSocket = sock.Detach();
		pThread->ResumeThread();
		
		this->m_thClient = pThread;
	}

	CSocket::OnAccept(nErrorCode);
}

// 소켓 종료시
void CServerSocket::OnClose(int nErrorCode)
{
	// 접속중이던 스레드가 있으면 삭제처리
	/*if (m_thClient != NULL) 
		m_thClient->PostThreadMessage(WM_QUIT, 0, 0);*/

	// 접속중이던 스레드가 있으면 삭제처리
	if (m_thClient != NULL) 
	{
		((CSocketThread *)m_thClient)->m_oClientSocket.Close();

		((CSocketThread *)m_thClient)->SuspendThread();
		((CSocketThread *)m_thClient)->ExitInstance();

		delete m_thClient;
	}

	CSocket::OnClose(nErrorCode);
}
