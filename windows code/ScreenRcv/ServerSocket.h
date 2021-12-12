#pragma once

// ���� ����
// CServerSocket ��� ����Դϴ�
class CClientSocket;
class CServerSocket : public CSocket
{
public:
	CServerSocket();
	virtual ~CServerSocket();

public:
	CWnd *m_pWnd;				// ���� ������ �ڵ�
	CWinThread *m_thClient;		// Ŭ���̾�Ʈ ������ ������ ������ �ڵ�

public:
	virtual void OnAccept(int nErrorCode);	// ���� ���Ž�
	virtual void OnClose(int nErrorCode);	// ���� �����
};


