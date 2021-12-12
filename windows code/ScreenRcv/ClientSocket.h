#pragma once

// CClientSocket ��� ����Դϴ�.

// Ŭ���̾�Ʈ ����
class CClientSocket : public CSocket
{
public:
	CClientSocket();
	virtual ~CClientSocket();

public:
	HWND m_hWnd;							// ���� ������ �ڵ�
	CWinThread *m_pThread;					// �ڽ��� ������ ������ �ڵ�
	CCriticalSection *m_pCritalSection;		// ���� �������� ũ��Ƽ�� ����
	BOOL m_bForceClear;						// ���� �����ΰ�?

public:
	virtual void OnReceive(int nErrorCode);
	virtual void OnClose(int nErrorCode);
};


