// SocketThread.cpp : ���� �����Դϴ�.
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
	// ������� �Ѱ��� ���� ȹ�� ó��
	m_oClientSocket.Attach(m_hSocket);	// ��Ŭ�� ������ �̿��Ͽ� ������ ���� ��ü�� �ʱ�ȭ��
	m_oClientSocket.m_pThread = this;	// �ڽ��� ���� ��ü�� ������ �����Ϳ� �ѱ�
	
	return TRUE;
}

int CSocketThread::ExitInstance()
{
	closesocket(this->m_hSocket);

	return CWinThread::ExitInstance();
}

BEGIN_MESSAGE_MAP(CSocketThread, CWinThread)
END_MESSAGE_MAP()


// CSocketThread �޽��� ó�����Դϴ�.

void CSocketThread::CloseSocket()
{
}