// SocketThread.cpp : 구현 파일입니다.
//

#include "stdafx.h"
#include "ScreenRcv.h"
#include "SocketThread.h"


// CSocketThread

IMPLEMENT_DYNCREATE(CSocketThread, CWinThread)

CSocketThread::CSocketThread()
{
}

CSocketThread::~CSocketThread()
{
}

BOOL CSocketThread::InitInstance()
{
	// 스레드로 넘겨진 소켓 획득 처리
	m_oClientSocket.Attach(m_hSocket);	// 버클리 소켓을 이용하여 포함한 소켓 객체를 초기화함
	m_oClientSocket.m_pThread = this;	// 자신을 소켓 객체의 스레드 포인터에 넘김
	
	return TRUE;
}

int CSocketThread::ExitInstance()
{
	closesocket(this->m_hSocket);

	return CWinThread::ExitInstance();
}

BEGIN_MESSAGE_MAP(CSocketThread, CWinThread)
END_MESSAGE_MAP()


// CSocketThread 메시지 처리기입니다.

void CSocketThread::CloseSocket()
{
}