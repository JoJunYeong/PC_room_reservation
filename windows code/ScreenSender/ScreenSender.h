
// ScreenSender.h : PROJECT_NAME ���� ���α׷��� ���� �� ��� �����Դϴ�.
//

#pragma once

#ifndef __AFXWIN_H__
	#error "PCH�� ���� �� ������ �����ϱ� ���� 'stdafx.h'�� �����մϴ�."
#endif

#include "resource.h"		// �� ��ȣ�Դϴ�.


// CScreenSenderApp:
// �� Ŭ������ ������ ���ؼ��� ScreenSender.cpp�� �����Ͻʽÿ�.
//

class CScreenSenderApp : public CWinApp
{
public:
	CScreenSenderApp();

// �������Դϴ�.
public:
	virtual BOOL InitInstance();

// �����Դϴ�.

	DECLARE_MESSAGE_MAP()
};

extern CScreenSenderApp theApp;