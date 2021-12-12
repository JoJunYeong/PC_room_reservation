#pragma once

// CClientSocket ��� ����Դϴ�.

// Ŭ���̾�Ʈ ����
class CClientSocket : public CSocket
{
public:
	CClientSocket();
	virtual ~CClientSocket();

public:
	CWnd *m_pWnd;							// ���� ������ �ڵ�
	CCriticalSection *m_pCritalSection;		// ũ��Ƽ�� ���� : ����ȭ��

	CString m_sPath;						// ���ϰ�� ����
	CString m_sFileName;					// �����̸� ����

public:
	virtual void OnReceive(int nErrorCode);	// ��Ŷ ���Ž�
	virtual void OnClose(int nErrorCode);	// ������ ������ ��
};


