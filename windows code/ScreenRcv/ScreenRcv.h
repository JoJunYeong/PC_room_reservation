
// ScreenRcv.h : PROJECT_NAME ���� ���α׷��� ���� �� ��� �����Դϴ�.
//

#pragma once

#ifndef __AFXWIN_H__
	#error "PCH�� ���� �� ������ �����ϱ� ���� 'stdafx.h'�� �����մϴ�."
#endif

#include "resource.h"		// �� ��ȣ�Դϴ�.


// CScreenRcvApp:
// �� Ŭ������ ������ ���ؼ��� ScreenRcv.cpp�� �����Ͻʽÿ�.
//

class CScreenRcvApp : public CWinApp
{
public:
	CScreenRcvApp();

// �������Դϴ�.
public:
	virtual BOOL InitInstance();

// �����Դϴ�.

	DECLARE_MESSAGE_MAP()
};

extern CScreenRcvApp theApp;