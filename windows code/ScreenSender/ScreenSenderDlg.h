
// ScreenSenderDlg.h : ��� ����
//

#pragma once

// CScreenSenderDlg ��ȭ ����
class CClientSocket;						// �ش� Ŭ������ �������� �˸� : �����ͷθ� ��밡��
class CScreenSenderDlg : public CDialogEx
{
// �����Դϴ�.
public:
	CScreenSenderDlg(CWnd* pParent = NULL);	// ǥ�� �������Դϴ�.

// ��ȭ ���� �������Դϴ�.
	enum { IDD = IDD_SCREENSENDER_DIALOG };

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
	CClientSocket *m_pClientSocket;			// ���� Ŭ���̾�Ʈ
	CCriticalSection m_oCriticalSection;	// ũ��Ƽ�� ���� : ����ȭ

public:
	CEdit m_edtStartLeft;		// ��Ʈ�� ������
	CEdit m_edtSTartTop;
	CEdit m_edtEndBottom;
	CEdit m_edtEndRight;
	CEdit m_edtSaveDirPath;
	CEdit m_edtSaveFileName;
	CStatic m_stState;
	CStatic m_stWorkState;
	
	CButton m_btnStart;
	CButton m_btnStop;
	CButton m_btnTest;

public:	
	BOOL ConnectToServer();				// ����
	BOOL DisConnectToServer();			// ���� ����
	BOOL CheckInputData();				// �� �Է� üũ : ��Ʈ�� ������ ���� üũ
	void SetStateText(int mode);		// ��� ���� ǥ��
	void SetWorkStateText(CString msg);	// ������ ó�� ���� ǥ��

	// ����� �޽��� �Լ�
	afx_msg LONG OnCaptureScreen(UINT wParam, LONG lParam);		// ȭ�� ĸó
	afx_msg LONG OnSendData(UINT wParam, LONG lParam);			// ���� �۽�
	afx_msg LONG OnSocketClosed(UINT wParam, LONG lParam);		// ���� �����

public:
	afx_msg void OnBnClickedBtnStart();
	afx_msg void OnBnClickedBtnStop();
	afx_msg void OnBnClickedBtnTest();
	afx_msg void OnTimer(UINT_PTR nIDEvent);	
	virtual BOOL PreTranslateMessage(MSG* pMsg);
};
