#pragma once

#include "ClientSocket.h"

// ��ſ� ������
// CSocketThread
class CSocketThread : public CWinThread
{
	DECLARE_DYNCREATE(CSocketThread)

protected:
	CSocketThread();           // ���� ����⿡ ���Ǵ� protected �������Դϴ�.
	virtual ~CSocketThread();

public:
	SOCKET m_hSocket;				// �����忡 �Ѱ��� ��Ŭ�� ���� ��ü
	CClientSocket m_oClientSocket;	// Ŭ���̾�Ʈ ���� ��ü

public:
	virtual BOOL InitInstance();
	virtual int ExitInstance();
	void CloseSocket();

protected:
	DECLARE_MESSAGE_MAP()
};


