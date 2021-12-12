
// ScreenRcvDlg.cpp : ���� ����
//

#include "stdafx.h"
#include "ScreenRcv.h"
#include "ScreenRcvDlg.h"
#include "afxdialogex.h"

#include "Defines.h"
#include "ServerSocket.h"
#include "SocketThread.h"

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


// CScreenRcvDlg ��ȭ ����

CScreenRcvDlg::CScreenRcvDlg(CWnd* pParent /*=NULL*/)
	: CDialogEx(CScreenRcvDlg::IDD, pParent)
{
	m_hIcon = AfxGetApp()->LoadIcon(IDR_MAINFRAME);

	m_pServerSocket = NULL;			// ���� ���� ��ü NULL�� �ʱ�ȭ
}

void CScreenRcvDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
	DDX_Control(pDX, IDC_BTN_START, m_btnStart);
	DDX_Control(pDX, IDC_BTN_STOP, m_btnStop);
	DDX_Control(pDX, IDC_LIST_LOG, m_lstLog);
}

BEGIN_MESSAGE_MAP(CScreenRcvDlg, CDialogEx)
	ON_WM_SYSCOMMAND()
	ON_WM_PAINT()
	ON_WM_QUERYDRAGICON()
	ON_BN_CLICKED(IDC_BTN_START, &CScreenRcvDlg::OnBnClickedBtnStart)
	ON_BN_CLICKED(IDC_BTN_STOP, &CScreenRcvDlg::OnBnClickedBtnStop)
	ON_MESSAGE(WM_UPDATE_LOG, OnUpdateLog)
	ON_MESSAGE(WM_CLIENT_CLOSED, OnClientClosed)
END_MESSAGE_MAP()


// CScreenRcvDlg �޽��� ó����

BOOL CScreenRcvDlg::OnInitDialog()
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

	// ��ư ����
	m_btnStart.EnableWindow(TRUE);
	m_btnStop.EnableWindow(FALSE);
	m_lstLog.ResetContent();

	return TRUE;  // ��Ŀ���� ��Ʈ�ѿ� �������� ������ TRUE�� ��ȯ�մϴ�.
}

void CScreenRcvDlg::OnSysCommand(UINT nID, LPARAM lParam)
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

void CScreenRcvDlg::OnPaint()
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
HCURSOR CScreenRcvDlg::OnQueryDragIcon()
{
	return static_cast<HCURSOR>(m_hIcon);
}

BOOL CScreenRcvDlg::PreTranslateMessage(MSG* pMsg)
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

// ���� ����
void CScreenRcvDlg::OnBnClickedBtnStart()
{
	if (m_pServerSocket == NULL)
	{
		// ���� �ʱ�ȭ
		if (!AfxSocketInit())
		{
			AfxMessageBox("���� �ʱ�ȭ ����!");
			return;
		}
		else 
		{
			// ���� ���� ��ü �ʱ�ȭ
			m_pServerSocket = new CServerSocket();
			m_pServerSocket->m_pWnd = this;

			// ���� ���� ����
			if (!m_pServerSocket->Create(SERVER_PORT))
			{
				AfxMessageBox("���� ���� ���� ����!");
				m_pServerSocket = NULL;
				return;
			}

			// ���� ���� ����
			if (!m_pServerSocket->Listen()) 
			{
				AfxMessageBox("���� ���� ���Ӵ�� �غ� ����!");
				m_pServerSocket = NULL;
				return;
			}

			// ��ư ����
			m_btnStart.EnableWindow(FALSE);
			m_btnStop.EnableWindow(TRUE);
			m_lstLog.ResetContent();

			// �α� 
			m_lstLog.AddString("���� �ʱ�ȭ �Ϸ� �� ���� Ŭ���̾�Ʈ ���� ���.");
		}
	}
}

// ���� ����
void CScreenRcvDlg::OnBnClickedBtnStop()
{
	// ���� ������ �����ϸ�
	if (m_pServerSocket != NULL)
	{
		if (m_pServerSocket->m_thClient != NULL)
		{
			m_pServerSocket->m_thClient->SuspendThread();
			m_pServerSocket->m_thClient->ExitInstance();
			m_pServerSocket->m_thClient->m_hThread = NULL;
		}
		// ���� ���� ��������
		m_pServerSocket->Close();
		m_pServerSocket = NULL;

		// ��ư ����
		m_btnStart.EnableWindow(TRUE);
		m_btnStop.EnableWindow(FALSE);
	}
}


// �޽��� �Լ� : �����ͼ۽�
LONG CScreenRcvDlg::OnUpdateLog(UINT wParam, LONG lParam)
{
	// �Ѱܹ��� ���ڿ��� �޾Ƽ�
	CString *tmp = (CString *)wParam;

	CString msg;
	msg.Format("%s", *tmp);

	// ����Ʈ �ڽ��� �߰��ϰ�
	m_lstLog.AddString(msg);

	// ����Ʈ �ڽ��� ������ ������Ʈ ��ġ�� ��ũ��
	m_lstLog.SetTopIndex((m_lstLog.GetCount() > 0 ? m_lstLog.GetCount() : 1) - 1);

	return 0;
}

// �޽��� �Լ� : Ŭ���̾�Ʈ ����
LONG CScreenRcvDlg::OnClientClosed(UINT wParam, LONG lParam)
{
	if (m_pServerSocket->m_thClient != NULL)
	{
		m_pServerSocket->m_thClient->SuspendThread();
		m_pServerSocket->m_thClient->ExitInstance();
		m_pServerSocket->m_thClient->m_hThread = NULL;
	}

	return 0;
}
