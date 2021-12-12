
// ScreenSenderDlg.h : 헤더 파일
//

#pragma once

// CScreenSenderDlg 대화 상자
class CClientSocket;						// 해당 클래스가 존재함을 알림 : 포인터로만 사용가능
class CScreenSenderDlg : public CDialogEx
{
// 생성입니다.
public:
	CScreenSenderDlg(CWnd* pParent = NULL);	// 표준 생성자입니다.

// 대화 상자 데이터입니다.
	enum { IDD = IDD_SCREENSENDER_DIALOG };

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
	CClientSocket *m_pClientSocket;			// 소켓 클라이언트
	CCriticalSection m_oCriticalSection;	// 크리티컬 섹션 : 동기화

public:
	CEdit m_edtStartLeft;		// 컨트롤 변수들
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
	BOOL ConnectToServer();				// 접속
	BOOL DisConnectToServer();			// 접속 해제
	BOOL CheckInputData();				// 값 입력 체크 : 컨트롤 변수로 값을 체크
	void SetStateText(int mode);		// 통신 상태 표시
	void SetWorkStateText(CString msg);	// 데이터 처리 상태 표시

	// 사용자 메시지 함수
	afx_msg LONG OnCaptureScreen(UINT wParam, LONG lParam);		// 화면 캡처
	afx_msg LONG OnSendData(UINT wParam, LONG lParam);			// 파일 송신
	afx_msg LONG OnSocketClosed(UINT wParam, LONG lParam);		// 소켓 종료됨

public:
	afx_msg void OnBnClickedBtnStart();
	afx_msg void OnBnClickedBtnStop();
	afx_msg void OnBnClickedBtnTest();
	afx_msg void OnTimer(UINT_PTR nIDEvent);	
	virtual BOOL PreTranslateMessage(MSG* pMsg);
};
