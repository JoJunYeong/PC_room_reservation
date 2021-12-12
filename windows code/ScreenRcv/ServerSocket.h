#pragma once

// 서버 소켓
// CServerSocket 명령 대상입니다
class CClientSocket;
class CServerSocket : public CSocket
{
public:
	CServerSocket();
	virtual ~CServerSocket();

public:
	CWnd *m_pWnd;				// 메인 윈도우 핸들
	CWinThread *m_thClient;		// 클라이언트 소켓을 포함한 스레드 핸들

public:
	virtual void OnAccept(int nErrorCode);	// 접속 수신시
	virtual void OnClose(int nErrorCode);	// 소켓 종료시
};


