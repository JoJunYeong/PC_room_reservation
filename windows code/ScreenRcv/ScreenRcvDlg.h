
// ScreenRcvDlg.h : ��� ����
//

#pragma once

// CScreenRcvDlg ��ȭ ����
class CServerSocket;						// ���� ���� ���� : �����͸� ��밡��
class CScreenRcvDlg : public CDialogEx
{
// �����Դϴ�.
public:
	CScreenRcvDlg(CWnd* pParent = NULL);	// ǥ�� �������Դϴ�.

// ��ȭ ���� �������Դϴ�.
	enum { IDD = IDD_SCREENRCV_DIALOG };

	protected:
	virtual void DoDataExchange(CDataExchange* pDX);	// DDX/DDV �����Դϴ�.


// �����Դϴ�.
protected:
	HICON m_hIcon;

	// ������ �޽��� �� �Լ�
	virtual BOOL OnInitDialog();
	afx_msg void OnSysCommand(UINT nID, LPARAM lParam);
	afx_msg void OnPaint();
	afx_msg HCURSOR OnQueryDragIcon();
	DECLARE_MESSAGE_MAP()

public:
	CServerSocket *m_pServerSocket;			// ���� ����
	CCriticalSection m_oCriticalSection;	// ũ��Ƽ�� ���� : ���� ����ȭ

public:
	CButton m_btnStart;		// ��Ʈ�� ����
	CButton m_btnStop;
	CListBox m_lstLog;

public:
	afx_msg LONG OnUpdateLog(UINT wParam, LONG lParam);		// ����Ʈ �ڽ� ������Ʈ �޽��� �Լ�
	afx_msg LONG OnClientClosed(UINT wParam, LONG lParam);	// Ŭ���̾�Ʈ ���� �޽��� �Լ�

public:
	afx_msg void OnBnClickedBtnStart();
	afx_msg void OnBnClickedBtnStop();	
	virtual BOOL PreTranslateMessage(MSG* pMsg);
};
