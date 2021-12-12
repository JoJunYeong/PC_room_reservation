
// ScreenSenderDlg.cpp : ���� ����
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


// ���� ���α׷� ������ ���Ǵ� CAboutDlg ��ȭ �����Դϴ�.

class CAboutDlg : public CDialogEx
{
public:
	CAboutDlg();

	// ��ȭ ���� �������Դϴ�.
	enum { IDD = IDD_ABOUTBOX };

protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV �����Դϴ�.

	// �����Դϴ�.
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


// CScreenSenderDlg ��ȭ ����

CScreenSenderDlg::CScreenSenderDlg(CWnd* pParent /*=NULL*/)
	: CDialogEx(CScreenSenderDlg::IDD, pParent)
{
	m_hIcon = AfxGetApp()->LoadIcon(IDR_MAINFRAME);

	m_pClientSocket = NULL;	// ���� ��ü NULL�� �ʱ�ȭ
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


// CScreenSenderDlg �޽��� ó����

BOOL CScreenSenderDlg::OnInitDialog()
{
	CDialogEx::OnInitDialog();

	// �ý��� �޴��� "����..." �޴� �׸��� �߰��մϴ�.

	// IDM_ABOUTBOX�� �ý��� ��� ������ �־�� �մϴ�.
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

	// �� ��ȭ ������ �������� �����մϴ�. ���� ���α׷��� �� â�� ��ȭ ���ڰ� �ƴ� ��쿡��
	//  �����ӿ�ũ�� �� �۾��� �ڵ����� �����մϴ�.
	SetIcon(m_hIcon, TRUE);			// ū �������� �����մϴ�.
	SetIcon(m_hIcon, FALSE);		// ���� �������� �����մϴ�.

	// �⺻�� ����
	m_edtStartLeft.SetWindowText("0");
	m_edtSTartTop.SetWindowText("0");
	SetStateText(3);
	SetWorkStateText("���");

	// ��ư ����
	m_btnStart.EnableWindow(TRUE);
	m_btnStop.EnableWindow(FALSE);

	return TRUE;  // ��Ŀ���� ��Ʈ�ѿ� �������� ������ TRUE�� ��ȯ�մϴ�.
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

// ��ȭ ���ڿ� �ּ�ȭ ���߸� �߰��� ��� �������� �׸�����
//  �Ʒ� �ڵ尡 �ʿ��մϴ�. ����/�� ���� ����ϴ� MFC ���� ���α׷��� ��쿡��
//  �����ӿ�ũ���� �� �۾��� �ڵ����� �����մϴ�.

void CScreenSenderDlg::OnPaint()
{
	if (IsIconic())
	{
		CPaintDC dc(this); // �׸��⸦ ���� ����̽� ���ؽ�Ʈ�Դϴ�.

		SendMessage(WM_ICONERASEBKGND, reinterpret_cast<WPARAM>(dc.GetSafeHdc()), 0);

		// Ŭ���̾�Ʈ �簢������ �������� ����� ����ϴ�.
		int cxIcon = GetSystemMetrics(SM_CXICON);
		int cyIcon = GetSystemMetrics(SM_CYICON);
		CRect rect;
		GetClientRect(&rect);
		int x = (rect.Width() - cxIcon + 1) / 2;
		int y = (rect.Height() - cyIcon + 1) / 2;

		// �������� �׸��ϴ�.
		dc.DrawIcon(x, y, m_hIcon);
	}
	else
	{
		CDialogEx::OnPaint();
	}
}

// ����ڰ� �ּ�ȭ�� â�� ���� ���ȿ� Ŀ���� ǥ�õǵ��� �ý��ۿ���
//  �� �Լ��� ȣ���մϴ�.
HCURSOR CScreenSenderDlg::OnQueryDragIcon()
{
	return static_cast<HCURSOR>(m_hIcon);
}

// ȭ�� ���� ���� ó��
BOOL CScreenSenderDlg::PreTranslateMessage(MSG* pMsg)
{
	//ALT + F4 ����
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
			pMsg->wParam == VK_CANCEL)       //CTRL + PAUSE ����
			return TRUE;        
	}

	return CDialogEx::PreTranslateMessage(pMsg);
}

// ����
void CScreenSenderDlg::OnBnClickedBtnStart()
{
	if (ConnectToServer()) 
	{
		// ��ư ����
		m_btnStart.EnableWindow(FALSE);
		m_btnStop.EnableWindow(TRUE);

		SetStateText(1);
	}
}

// ����
void CScreenSenderDlg::OnBnClickedBtnStop()
{
	if (DisConnectToServer())
	{
		// ��ư ����
		m_btnStart.EnableWindow(TRUE);
		m_btnStop.EnableWindow(FALSE);

		SetStateText(3);
		SetWorkStateText("���� ���� ����");
	}
}

// �׽�Ʈ �� ��ư
void CScreenSenderDlg::OnBnClickedBtnTest()
{
	// ���� �۽� 1ȸ�� �׽�Ʈ
	if (m_pClientSocket != NULL) 
	{
		int send_packet = PACKET_HEAD;
		m_pClientSocket->Send(&send_packet, 4);
	}
}

// Ÿ�̸� �Լ�
void CScreenSenderDlg::OnTimer(UINT_PTR nIDEvent)
{
	// ó��
	if (nIDEvent == TIMER_SCREEN)		// ȭ��ĸ���� ���
	{
		this->SendMessage(WM_CAPTURE_SCREEN);	// ȭ��ĸó ��û
	}
	else if (nIDEvent == TIMER_SEND)	// ���� �۽��� ���
	{
		this->SendMessage(WM_SEND_DATA);	// ���� �۽� ��û
	}
	else if (nIDEvent == TIMER_RECONNECT)	// ������ �ϰ��
	{
		KillTimer(TIMER_RECONNECT);	// �켱 Ÿ�̸� �ٽ� ����

		ConnectToServer();			// ���� �ٽ� ���� ��û
	}

	CDialogEx::OnTimer(nIDEvent);
}

// ����
BOOL CScreenSenderDlg::ConnectToServer()
{
	if (m_pClientSocket == NULL)
	{
		// �Է� �� üũ
		if(CheckInputData())
		{
			// ���� �ʱ�ȭ
			if (!AfxSocketInit())
			{
				//AfxMessageBox("���� �ʱ�ȭ ����!");
				SetTimer(TIMER_RECONNECT, TIMER_INTERVAL_RECONNECT, NULL);
				SetWorkStateText("���� �ʱ�ȭ ����, 5�е� �ٽ� �����մϴ�.");
				SetStateText(2);

				return FALSE;
			}
			else 
			{
				// �Է°� �о����
				CString sDirPath, sFileName;
				m_edtSaveDirPath.GetWindowText(sDirPath);
				m_edtSaveFileName.GetWindowText(sFileName);

				// ���� ��ü �ʱ�ȭ
				m_pClientSocket = new CClientSocket();
				m_pClientSocket->m_pWnd = this;
				m_pClientSocket->m_sPath = sDirPath;
				m_pClientSocket->m_sFileName = sFileName;

				// ���� ����
				if (!m_pClientSocket->Create())
				{
					//AfxMessageBox("���� ���� ����!");
					m_pClientSocket = NULL;
					SetTimer(TIMER_RECONNECT, TIMER_INTERVAL_RECONNECT, NULL);
					SetWorkStateText("���� ���� ����, 5�е� �ٽ� �����մϴ�.");
					SetStateText(2);

					return FALSE;
				}

				// ���� ����
				if (!m_pClientSocket->Connect(SERVER_ADDR, SERVER_PORT))
				{
					//AfxMessageBox("���� ���� ����!");
					m_pClientSocket = NULL;
					SetTimer(TIMER_RECONNECT, TIMER_INTERVAL_RECONNECT, NULL);
					SetWorkStateText("���� ���� ����, 5�е� �ٽ� �����մϴ�.");
					SetStateText(2);

					return FALSE;
				}

				// Ÿ�̸� �ʱ�ȭ
				SetTimer(TIMER_SCREEN, TIMER_INTERVAL_SCREEN, NULL);
				SetTimer(TIMER_SEND, TIMER_INTERVAL_SEND, NULL);

				SetWorkStateText("���� ���� ����");
				return TRUE;
			}
		}
	}

	return FALSE;
}

// ���� ����
BOOL CScreenSenderDlg::DisConnectToServer()
{
	// Ÿ�̸� ����
	KillTimer(TIMER_SCREEN);
	KillTimer(TIMER_SEND);

	// ������ ������ ���¶�� 
	// �� ����Ǿ��� �ƴϰ� ������ �����Ǿ� �ִٸ� ���� ó��
	if (m_pClientSocket != NULL) 
	{
		// ���� ����
		m_pClientSocket->Close();
		m_pClientSocket = NULL;	
	}

	return TRUE;
}

// ������ üũ
BOOL CScreenSenderDlg::CheckInputData()
{
	// �� üũ ����
	CString sStartLeft, sStartTop, sEndRight, sEndBottom, sDirPath, sFileName;

	// ��Ʈ���� ���� �о��
	m_edtStartLeft.GetWindowText(sStartLeft);
	m_edtSTartTop.GetWindowText(sStartTop);
	m_edtEndRight.GetWindowText(sEndRight);
	m_edtEndBottom.GetWindowText(sEndBottom);
	m_edtSaveDirPath.GetWindowText(sDirPath);
	m_edtSaveFileName.GetWindowText(sFileName);

	// ��üũ
	if (sStartLeft.GetLength() <= 0 || sStartTop.GetLength() <= 0)	// ���� ��ǥ
	{
		AfxMessageBox("���� ��ǥ ������ �Է��ϼ���.");
		return FALSE;
	}

	if (sEndRight.GetLength() <= 0 || sEndBottom.GetLength() <= 0)	// �� ��ǥ
	{
		AfxMessageBox("�� ��ǥ ������ �Է��ϼ���.");
		return FALSE;
	}

	if (sDirPath.GetLength() <= 0 || sFileName.GetLength() <= 0)	// ���� ����
	{
		AfxMessageBox("��� �Ǵ� ���� ������ �Է��ϼ���.");
		return FALSE;
	}

	return TRUE;
}

// ��� ���� ǥ��
void CScreenSenderDlg::SetStateText(int mode)
{
	if (mode == 1) 
		m_stState.SetWindowText("������");
	else if (mode == 2) 
		m_stState.SetWindowText("�� ������");
	else if (mode == 3) 
		m_stState.SetWindowText("������");
}

// ������ ó�� ���� ǥ��
void CScreenSenderDlg::SetWorkStateText(CString msg)
{
	m_stWorkState.SetWindowText(msg);
}

// �޽��� �Լ� : ȭ�� ĸó
LONG CScreenSenderDlg::OnCaptureScreen(UINT wParam, LONG lParam)
{
	// �Է°� ����
	CString sStartLeft, sStartTop, sEndRight, sEndBottom, sDirPath, sFileName;
	m_edtStartLeft.GetWindowText(sStartLeft);
	m_edtSTartTop.GetWindowText(sStartTop);
	m_edtEndRight.GetWindowText(sEndRight);
	m_edtEndBottom.GetWindowText(sEndBottom);
	m_edtSaveDirPath.GetWindowText(sDirPath);
	m_edtSaveFileName.GetWindowText(sFileName);

	// ������ ȯ��
	int sx =0, sy = 0, ex =0, ey = 0;
	sx = _tstoi(sStartLeft.GetBuffer());
	sy = _tstoi(sStartTop.GetBuffer());
	ex = _tstoi(sEndRight.GetBuffer());
	ey = _tstoi(sEndBottom.GetBuffer());
	sStartLeft.ReleaseBuffer();
	sStartTop.ReleaseBuffer();
	sEndRight.ReleaseBuffer();
	sEndBottom.ReleaseBuffer();

	// ���� ��� ����
	CreateDirectory(sDirPath, NULL);

	// ����ȭ�� ĸ��
	CWnd *pWndDesktop = GetDesktopWindow();

	//�̹�����ü�� ����ȭ�� �׸�������DC
	CDC* ScrDC = nullptr;
	CWindowDC wdc(pWndDesktop);
	CClientDC dc(this);

	CImage Image;
	Image.Create(ex - sx, ey - sy, wdc.GetDeviceCaps(BITSPIXEL)); //����ִ� �̹���

	ScrDC = CDC::FromHandle(Image.GetDC());
	ScrDC->BitBlt(0, 0, ex - sx, ex - sy, &wdc, sx, sy, SRCCOPY);

	// ���� ����ø� ũ��Ƽ�� ������ �̿��Ͽ� ���� �۽źο��� 
	// ���� ���� �� �� ���� ó����.
	m_oCriticalSection.Lock();
	Image.Save(sDirPath + "\\" + sFileName+ ".jpg", Gdiplus::ImageFormatJPEG);
	m_oCriticalSection.Unlock();

	Image.ReleaseDC();
	ReleaseDC(ScrDC);

	return 0;
}

// �޽��� �Լ� : �����ͼ۽�
LONG CScreenSenderDlg::OnSendData(UINT wParam, LONG lParam)
{
	// ������ �ִٸ� �۽�
	if (m_pClientSocket != NULL) 
	{
		int send_packet = PACKET_HEAD;
		m_pClientSocket->Send(&send_packet, 4);
	}

	return 0;
}

// ���� �����
LONG CScreenSenderDlg::OnSocketClosed(UINT wParam, LONG lParam)
{
	// ���� ��ȭ
	m_pClientSocket = NULL;
	DisConnectToServer();

	// ���� ����
	SetStateText(2);
	ConnectToServer();

	return 0;
}

