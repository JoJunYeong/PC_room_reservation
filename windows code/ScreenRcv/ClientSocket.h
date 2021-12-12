#pragma once

// CClientSocket 명령 대상입니다.

// 클라이언트 소켓
class CClientSocket : public CSocket
{
public:
	CClientSocket();
	virtual ~CClientSocket();

public:
	HWND m_hWnd;							// 메인 윈도우 핸들
	CWinThread *m_pThread;					// 자신이 포함한 스레드 핸들
	CCriticalSection *m_pCritalSection;		// 메인 윈도우의 크리티컬 섹션
	BOOL m_bForceClear;						// 강제 종료인가?

public:
	virtual void OnReceive(int nErrorCode);
	virtual void OnClose(int nErrorCode);
};


