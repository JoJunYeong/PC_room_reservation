
// ScreenSenderDlg.cpp : 구현 파일
//

#include "stdafx.h"
#include "ScreenSender.h"
#include "ScreenSenderDlg.h"
#include "afxdialogex.h"

#include "Defines.h"
#include "ClientSocket.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#endif


// 응용 프로그램 정보에 사용되는 CAboutDlg 대화 상자입니다.

class CAboutDlg : public CDialogEx
{
public:
	CAboutDlg();

	// 대화 상자 데이터입니다.
	enum { IDD = IDD_ABOUTBOX };

protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV 지원입니다.

	// 구현입니다.
protected:
	DECLARE_MESSAGE_MAP()
};

CAboutDlg::CAboutDlg() : CDialogEx(CAboutDlg::IDD)
{
}

void CAboutDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
}

BEGIN_MESSAGE_MAP(CAboutDlg, CDialogEx)
END_MESSAGE_MAP()


// CScreenSenderDlg 대화 상자

CScreenSenderDlg::CScreenSenderDlg(CWnd* pParent /*=NULL*/)
	: CDialogEx(CScreenSenderDlg::IDD, pParent)
{
	m_hIcon = AfxGetApp()->LoadIcon(IDR_MAINFRAME);

	m_pClientSocket = NULL;	// 소켓 객체 NULL로 초기화
}

void CScreenSenderDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
	DDX_Control(pDX, IDC_BTN_START, m_btnStart);
	DDX_Control(pDX, IDC_BTN_STOP, m_btnStop);
	DDX_Control(pDX, IDC_EDIT_END_BOTTOM, m_edtEndBottom);
	DDX_Control(pDX, IDC_EDIT_END_RIGHT, m_edtEndRight);
	DDX_Control(pDX, IDC_EDIT_SAVE_DIRPATH, m_edtSaveDirPath);
	DDX_Control(pDX, IDC_EDIT_SAVE_FILENAME, m_edtSaveFileName);
	DDX_Control(pDX, IDC_EDIT_START_LEFT, m_edtStartLeft);
	DDX_Control(pDX, IDC_EDIT_START_Top, m_edtSTartTop);
	DDX_Control(pDX, IDC_STATIC_STATE, m_stState);
	DDX_Control(pDX, IDC_STATIC_WORK_STATE, m_stWorkState);
	DDX_Control(pDX, IDC_BTN_TEST, m_btnTest);
}

BEGIN_MESSAGE_MAP(CScreenSenderDlg, CDialogEx)
	ON_WM_SYSCOMMAND()
	ON_WM_PAINT()
	ON_WM_QUERYDRAGICON()
	ON_BN_CLICKED(IDC_BTN_START, &CScreenSenderDlg::OnBnClickedBtnStart)
	ON_BN_CLICKED(IDC_BTN_STOP, &CScreenSenderDlg::OnBnClickedBtnStop)
	ON_BN_CLICKED(IDC_BTN_TEST, &CScreenSenderDlg::OnBnClickedBtnTest)
	ON_WM_TIMER()
	ON_MESSAGE(WM_CAPTURE_SCREEN, OnCaptureScreen)
	ON_MESSAGE(WM_SEND_DATA, OnSendData)	
	ON_MESSAGE(WM_SOCKET_CLOSED, OnSocketClosed)	
END_MESSAGE_MAP()


// CScreenSenderDlg 메시지 처리기

BOOL CScreenSenderDlg::OnInitDialog()
{
	CDialogEx::OnInitDialog();

	// 시스템 메뉴에 "정보..." 메뉴 항목을 추가합니다.

	// IDM_ABOUTBOX는 시스템 명령 범위에 있어야 합니다.
	ASSERT((IDM_ABOUTBOX & 0xFFF0) == IDM_ABOUTBOX);
	ASSERT(IDM_ABOUTBOX < 0xF000);

	CMenu* pSysMenu = GetSystemMenu(FALSE);
	if (pSysMenu != NULL)
	{
		BOOL bNameValid;
		CString strAboutMenu;
		bNameValid = strAboutMenu.LoadString(IDS_ABOUTBOX);
		ASSERT(bNameValid);
		if (!strAboutMenu.IsEmpty())
		{
			pSysMenu->AppendMenu(MF_SEPARATOR);
			pSysMenu->AppendMenu(MF_STRING, IDM_ABOUTBOX, strAboutMenu);
		}
	}

	// 이 대화 상자의 아이콘을 설정합니다. 응용 프로그램의 주 창이 대화 상자가 아닐 경우에는
	//  프레임워크가 이 작업을 자동으로 수행합니다.
	SetIcon(m_hIcon, TRUE);			// 큰 아이콘을 설정합니다.
	SetIcon(m_hIcon, FALSE);		// 작은 아이콘을 설정합니다.

	// 기본값 설정
	m_edtStartLeft.SetWindowText("0");
	m_edtSTartTop.SetWindowText("0");
	SetStateText(3);
	SetWorkStateText("대기");

	// 버튼 설정
	m_btnStart.EnableWindow(TRUE);
	m_btnStop.EnableWindow(FALSE);

	return TRUE;  // 포커스를 컨트롤에 설정하지 않으면 TRUE를 반환합니다.
}

void CScreenSenderDlg::OnSysCommand(UINT nID, LPARAM lParam)
{
	if ((nID & 0xFFF0) == IDM_ABOUTBOX)
	{
		CAboutDlg dlgAbout;
		dlgAbout.DoModal();
	}
	else
	{
		CDialogEx::OnSysCommand(nID, lParam);
	}
}

// 대화 상자에 최소화 단추를 추가할 경우 아이콘을 그리려면
//  아래 코드가 필요합니다. 문서/뷰 모델을 사용하는 MFC 응용 프로그램의 경우에는
//  프레임워크에서 이 작업을 자동으로 수행합니다.

void CScreenSenderDlg::OnPaint()
{
	if (IsIconic())
	{
		CPaintDC dc(this); // 그리기를 위한 디바이스 컨텍스트입니다.

		SendMessage(WM_ICONERASEBKGND, reinterpret_cast<WPARAM>(dc.GetSafeHdc()), 0);

		// 클라이언트 사각형에서 아이콘을 가운데에 맞춥니다.
		int cxIcon = GetSystemMetrics(SM_CXICON);
		int cyIcon = GetSystemMetrics(SM_CYICON);
		CRect rect;
		GetClientRect(&rect);
		int x = (rect.Width() - cxIcon + 1) / 2;
		int y = (rect.Height() - cyIcon + 1) / 2;

		// 아이콘을 그립니다.
		dc.DrawIcon(x, y, m_hIcon);
	}
	else
	{
		CDialogEx::OnPaint();
	}
}

// 사용자가 최소화된 창을 끄는 동안에 커서가 표시되도록 시스템에서
//  이 함수를 호출합니다.
HCURSOR CScreenSenderDlg::OnQueryDragIcon()
{
	return static_cast<HCURSOR>(m_hIcon);
}

// 화면 닫힘 방지 처리
BOOL CScreenSenderDlg::PreTranslateMessage(MSG* pMsg)
{
	//ALT + F4 방지
	if(pMsg->message == WM_SYSKEYDOWN)
	{
		if(pMsg->wParam == VK_F4)
			return TRUE;
	}

	if(pMsg->message == WM_KEYDOWN)
	{
		if(pMsg->wParam == VK_ESCAPE  || 
			pMsg->wParam == VK_RETURN ||
			pMsg->wParam == VK_SPACE  ||
			pMsg->wParam == VK_CANCEL)       //CTRL + PAUSE 방지
			return TRUE;        
	}

	return CDialogEx::PreTranslateMessage(pMsg);
}

// 시작
void CScreenSenderDlg::OnBnClickedBtnStart()
{
	if (ConnectToServer()) 
	{
		// 버튼 설정
		m_btnStart.EnableWindow(FALSE);
		m_btnStop.EnableWindow(TRUE);

		SetStateText(1);
	}
}

// 종료
void CScreenSenderDlg::OnBnClickedBtnStop()
{
	if (DisConnectToServer())
	{
		// 버튼 설정
		m_btnStart.EnableWindow(TRUE);
		m_btnStop.EnableWindow(FALSE);

		SetStateText(3);
		SetWorkStateText("서버 중지 성공");
	}
}

// 테스트 용 버튼
void CScreenSenderDlg::OnBnClickedBtnTest()
{
	// 파일 송신 1회성 테스트
	if (m_pClientSocket != NULL) 
	{
		int send_packet = PACKET_HEAD;
		m_pClientSocket->Send(&send_packet, 4);
	}
}

// 타이머 함수
void CScreenSenderDlg::OnTimer(UINT_PTR nIDEvent)
{
	// 처리
	if (nIDEvent == TIMER_SCREEN)		// 화면캡쳐일 경우
	{
		this->SendMessage(WM_CAPTURE_SCREEN);	// 화면캡처 요청
	}
	else if (nIDEvent == TIMER_SEND)	// 파일 송신일 경우
	{
		this->SendMessage(WM_SEND_DATA);	// 파일 송신 요청
	}
	else if (nIDEvent == TIMER_RECONNECT)	// 재접속 일경우
	{
		KillTimer(TIMER_RECONNECT);	// 우선 타이머 다시 종료

		ConnectToServer();			// 서버 다시 접속 요청
	}

	CDialogEx::OnTimer(nIDEvent);
}

// 접속
BOOL CScreenSenderDlg::ConnectToServer()
{
	if (m_pClientSocket == NULL)
	{
		// 입력 값 체크
		if(CheckInputData())
		{
			// 소켓 초기화
			if (!AfxSocketInit())
			{
				//AfxMessageBox("소켓 초기화 실패!");
				SetTimer(TIMER_RECONNECT, TIMER_INTERVAL_RECONNECT, NULL);
				SetWorkStateText("소켓 초기화 실패, 5분뒤 다시 접속합니다.");
				SetStateText(2);

				return FALSE;
			}
			else 
			{
				// 입력값 읽어오기
				CString sDirPath, sFileName;
				m_edtSaveDirPath.GetWindowText(sDirPath);
				m_edtSaveFileName.GetWindowText(sFileName);

				// 소켓 객체 초기화
				m_pClientSocket = new CClientSocket();
				m_pClientSocket->m_pWnd = this;
				m_pClientSocket->m_sPath = sDirPath;
				m_pClientSocket->m_sFileName = sFileName;

				// 소켓 생성
				if (!m_pClientSocket->Create())
				{
					//AfxMessageBox("소켓 생성 실패!");
					m_pClientSocket = NULL;
					SetTimer(TIMER_RECONNECT, TIMER_INTERVAL_RECONNECT, NULL);
					SetWorkStateText("소켓 생성 실패, 5분뒤 다시 접속합니다.");
					SetStateText(2);

					return FALSE;
				}

				// 서버 접속
				if (!m_pClientSocket->Connect(SERVER_ADDR, SERVER_PORT))
				{
					//AfxMessageBox("서버 접속 실패!");
					m_pClientSocket = NULL;
					SetTimer(TIMER_RECONNECT, TIMER_INTERVAL_RECONNECT, NULL);
					SetWorkStateText("서버 접속 실패, 5분뒤 다시 접속합니다.");
					SetStateText(2);

					return FALSE;
				}

				// 타이머 초기화
				SetTimer(TIMER_SCREEN, TIMER_INTERVAL_SCREEN, NULL);
				SetTimer(TIMER_SEND, TIMER_INTERVAL_SEND, NULL);

				SetWorkStateText("서버 접속 성공");
				return TRUE;
			}
		}
	}

	return FALSE;
}

// 접속 해제
BOOL CScreenSenderDlg::DisConnectToServer()
{
	// 타이머 해제
	KillTimer(TIMER_SCREEN);
	KillTimer(TIMER_SEND);

	// 소켓이 생성된 상태라면 
	// 즉 연결되엇건 아니건 소켓이 생서되어 있다면 종료 처리
	if (m_pClientSocket != NULL) 
	{
		// 소켓 닫음
		m_pClientSocket->Close();
		m_pClientSocket = NULL;	
	}

	return TRUE;
}

// 데이터 체크
BOOL CScreenSenderDlg::CheckInputData()
{
	// 값 체크 변수
	CString sStartLeft, sStartTop, sEndRight, sEndBottom, sDirPath, sFileName;

	// 컨트롤의 값을 읽어옴
	m_edtStartLeft.GetWindowText(sStartLeft);
	m_edtSTartTop.GetWindowText(sStartTop);
	m_edtEndRight.GetWindowText(sEndRight);
	m_edtEndBottom.GetWindowText(sEndBottom);
	m_edtSaveDirPath.GetWindowText(sDirPath);
	m_edtSaveFileName.GetWindowText(sFileName);

	// 값체크
	if (sStartLeft.GetLength() <= 0 || sStartTop.GetLength() <= 0)	// 시작 좌표
	{
		AfxMessageBox("시작 좌표 정보를 입력하세요.");
		return FALSE;
	}

	if (sEndRight.GetLength() <= 0 || sEndBottom.GetLength() <= 0)	// 끝 좌표
	{
		AfxMessageBox("끝 좌표 정보를 입력하세요.");
		return FALSE;
	}

	if (sDirPath.GetLength() <= 0 || sFileName.GetLength() <= 0)	// 파일 정보
	{
		AfxMessageBox("경로 또는 파일 정보를 입력하세요.");
		return FALSE;
	}

	return TRUE;
}

// 통신 상태 표시
void CScreenSenderDlg::SetStateText(int mode)
{
	if (mode == 1) 
		m_stState.SetWindowText("접속중");
	else if (mode == 2) 
		m_stState.SetWindowText("재 접속중");
	else if (mode == 3) 
		m_stState.SetWindowText("중지됨");
}

// 데이터 처리 상태 표시
void CScreenSenderDlg::SetWorkStateText(CString msg)
{
	m_stWorkState.SetWindowText(msg);
}

// 메시지 함수 : 화면 캡처
LONG CScreenSenderDlg::OnCaptureScreen(UINT wParam, LONG lParam)
{
	// 입력값 읽음
	CString sStartLeft, sStartTop, sEndRight, sEndBottom, sDirPath, sFileName;
	m_edtStartLeft.GetWindowText(sStartLeft);
	m_edtSTartTop.GetWindowText(sStartTop);
	m_edtEndRight.GetWindowText(sEndRight);
	m_edtEndBottom.GetWindowText(sEndBottom);
	m_edtSaveDirPath.GetWindowText(sDirPath);
	m_edtSaveFileName.GetWindowText(sFileName);

	// 데이터 환산
	int sx =0, sy = 0, ex =0, ey = 0;
	sx = _tstoi(sStartLeft.GetBuffer());
	sy = _tstoi(sStartTop.GetBuffer());
	ex = _tstoi(sEndRight.GetBuffer());
	ey = _tstoi(sEndBottom.GetBuffer());
	sStartLeft.ReleaseBuffer();
	sStartTop.ReleaseBuffer();
	sEndRight.ReleaseBuffer();
	sEndBottom.ReleaseBuffer();

	// 파일 경로 생성
	CreateDirectory(sDirPath, NULL);

	// 바탕화면 캡쳐
	CWnd *pWndDesktop = GetDesktopWindow();

	//이미지객체에 바탕화면 그리기위한DC
	CDC* ScrDC = nullptr;
	CWindowDC wdc(pWndDesktop);
	CClientDC dc(this);

	CImage Image;
	Image.Create(ex - sx, ey - sy, wdc.GetDeviceCaps(BITSPIXEL)); //비어있는 이미지

	ScrDC = CDC::FromHandle(Image.GetDC());
	ScrDC->BitBlt(0, 0, ex - sx, ex - sy, &wdc, sx, sy, SRCCOPY);

	// 파일 저장시만 크리티컬 섹션을 이용하여 파일 송신부에서 
	// 병렬 참조 할 수 없게 처리함.
	m_oCriticalSection.Lock();
	Image.Save(sDirPath + "\\" + sFileName+ ".jpg", Gdiplus::ImageFormatJPEG);
	m_oCriticalSection.Unlock();

	Image.ReleaseDC();
	ReleaseDC(ScrDC);

	return 0;
}

// 메시지 함수 : 데이터송신
LONG CScreenSenderDlg::OnSendData(UINT wParam, LONG lParam)
{
	// 소켓이 있다면 송신
	if (m_pClientSocket != NULL) 
	{
		int send_packet = PACKET_HEAD;
		m_pClientSocket->Send(&send_packet, 4);
	}

	return 0;
}

// 소켓 종료됨
LONG CScreenSenderDlg::OnSocketClosed(UINT wParam, LONG lParam)
{
	// 소켓 널화
	m_pClientSocket = NULL;
	DisConnectToServer();

	// 소켓 접속
	SetStateText(2);
	ConnectToServer();

	return 0;
}

