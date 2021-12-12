#pragma once

// CClientSocket 명령 대상입니다.

// 클라이언트 소켓
class CClientSocket : public CSocket
{
public:
	CClientSocket();
	virtual ~CClientSocket();

public:
	CWnd *m_pWnd;							// 메인 윈도우 핸들
	CCriticalSection *m_pCritalSection;		// 크리티컬 섹션 : 동기화용

	CString m_sPath;						// 파일경로 설정
	CString m_sFileName;					// 파일이름 설정

public:
	virtual void OnReceive(int nErrorCode);	// 패킷 수신시
	virtual void OnClose(int nErrorCode);	// 소켓이 닫혔을 때
};


