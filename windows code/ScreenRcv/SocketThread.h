#pragma once

#include "ClientSocket.h"

// 통신용 스레드
// CSocketThread
class CSocketThread : public CWinThread
{
	DECLARE_DYNCREATE(CSocketThread)

protected:
	CSocketThread();           // 동적 만들기에 사용되는 protected 생성자입니다.
	virtual ~CSocketThread();

public:
	SOCKET m_hSocket;				// 스레드에 넘겨줄 버클리 소켓 객체
	CClientSocket m_oClientSocket;	// 클라이언트 소켓 객체

public:
	virtual BOOL InitInstance();
	virtual int ExitInstance();
	void CloseSocket();

protected:
	DECLARE_MESSAGE_MAP()
};


