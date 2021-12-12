#pragma once

// ����� �޽���
#define WM_CAPTURE_SCREEN WM_USER + 100		// ȭ�� ĸó �޽���
#define WM_SEND_DATA WM_USER + 101			// ���� �۽� �޽���
#define WM_SOCKET_CLOSED WM_USER + 102		// ���� �۽� �޽���

// Ÿ�̸� ���� ��
#define TIMER_SCREEN 1					// ��ȣ 1
#define TIMER_SEND 2					// ��ȣ 2
#define TIMER_RECONNECT 3				// ��ȣ 3
#define TIMER_INTERVAL_SCREEN 1000		// ���͹� 1
#define TIMER_INTERVAL_SEND 5000		// ���͹� 2
#define TIMER_INTERVAL_RECONNECT 5000	// ���͹� 3
//#define TIMER_INTERVAL_RECONNECT 300000	// ���͹� 3

// ���� ���� ����
#define SERVER_ADDR "192.168.11.10"		// ������
//#define SERVER_ADDR "127.0.0.1"		// ������
#define SERVER_PORT 10002			// ��Ʈ

// ��Ŷ �⺻����
#define PACKET_BUFF_MAX 4096		// �⺻ ��Ŷ ũ��

#define PACKET_HEAD 1000			// ��Ŷ ����
#define PACKET_FILE_START 1100		// ���� ��û
#define PACKET_TAIL 2000			// ��Ŷ ��
#define PACKET_OK 5000				// ó�� �Ϸ�
