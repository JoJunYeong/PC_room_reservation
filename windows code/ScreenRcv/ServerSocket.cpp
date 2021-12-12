// ServerSocket.cpp : ���� �����Դϴ�.
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


// CServerSocket ��� �Լ�

// ���� ���Ž�
void CServerSocket::OnAccept(int nErrorCode)
{
	// ���� ���Ž�
	if (nErrorCode == 0) 
	{
		// ���ӵ� ������ �ϴ� �ް�
		CSocket sock;
		Accept(sock);

		// Ŭ���̾�Ʈ�� �����带 ����
		CSocketThread *pThread = (CSocketThread *)AfxBeginThread(RUNTIME_CLASS(CSocketThread), THREAD_PRIORITY_NORMAL, 0, CREATE_SUSPENDED);
		if (pThread == NULL)
		{
			sock.Close();
			return;
		}

		// ������ �� Ŭ���̾�Ʈ ���� �ʱ�ȭ
		CScreenRcvDlg *pDlg = (CScreenRcvDlg *) this->m_pWnd;
		if (pDlg != NULL) 
		{
			// �α� �޽��� ó��
			CString sPeerName;
			UINT nPeerPort = -1;
			if (sock.GetPeerName(sPeerName, nPeerPort))
			{
				CString msg = "Ŭ���̾�Ʈ(" + sPeerName + ") ���ӵ�.";
				pDlg->SendMessage(WM_UPDATE_LOG, (WPARAM)&msg, 0);
			}

			// ���� ������ ������ �ѱ�
			pThread->m_oClientSocket.m_hWnd = pDlg->GetSafeHwnd();
			pThread->m_oClientSocket.m_pCritalSection = &pDlg->m_oCriticalSection;
		}

		// ���� �и� �� ������ ���� ���� ó��
		pThread->m_hSocket = sock.Detach();
		pThread->ResumeThread();
		
		this->m_thClient = pThread;
	}

	CSocket::OnAccept(nErrorCode);
}

// ���� �����
void CServerSocket::OnClose(int nErrorCode)
{
	// �������̴� �����尡 ������ ����ó��
	/*if (m_thClient != NULL) 
		m_thClient->PostThreadMessage(WM_QUIT, 0, 0);*/

	// �������̴� �����尡 ������ ����ó��
	if (m_thClient != NULL) 
	{
		((CSocketThread *)m_thClient)->m_oClientSocket.Close();

		((CSocketThread *)m_thClient)->SuspendThread();
		((CSocketThread *)m_thClient)->ExitInstance();

		delete m_thClient;
	}

	CSocket::OnClose(nErrorCode);
}
