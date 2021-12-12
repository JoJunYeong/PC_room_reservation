
// ScreenRcvDlg.h : 헤더 파일
//

#pragma once

// CScreenRcvDlg 대화 상자
class CServerSocket;						// 서버 소켓 선언 : 포인터만 사용가능
class CScreenRcvDlg : public CDialogEx
{
// 생성입니다.
public:
	CScreenRcvDlg(CWnd* pParent = NULL);	// 표준 생성자입니다.

// 대화 상자 데이터입니다.
	enum { IDD = IDD_SCREENRCV_DIALOG };

	protected:
	virtual void DoDataExchange(CDataExchange* pDX);	// DDX/DDV 지원입니다.


// 구현입니다.
protected:
	HICON m_hIcon;

	// 생성된 메시지 맵 함수
	virtual BOOL OnInitDialog();
	afx_msg void OnSysCommand(UINT nID, LPARAM lParam);
	afx_msg void OnPaint();
	afx_msg HCURSOR OnQueryDragIcon();
	DECLARE_MESSAGE_MAP()

public:
	CServerSocket *m_pServerSocket;			// 서버 소켓
	CCriticalSection m_oCriticalSection;	// 크리티컬 섹션 : 파일 동기화

public:
	CButton m_btnStart;		// 컨트롤 변수
	CButton m_btnStop;
	CListBox m_lstLog;

public:
	afx_msg LONG OnUpdateLog(UINT wParam, LONG lParam);		// 리스트 박스 업데이트 메시지 함수
	afx_msg LONG OnClientClosed(UINT wParam, LONG lParam);	// 클라이언트 종료 메시지 함수

public:
	afx_msg void OnBnClickedBtnStart();
	afx_msg void OnBnClickedBtnStop();	
	virtual BOOL PreTranslateMessage(MSG* pMsg);
};
