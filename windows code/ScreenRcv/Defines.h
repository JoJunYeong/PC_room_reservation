#pragma once

// ����� �޽���
#define WM_UPDATE_LOG WM_USER + 100
#define WM_CLIENT_CLOSED WM_USER + 101

// ���� ����
#define SERVER_PORT 10002	// ���� ��Ʈ

// ��Ŷ �⺻����
#define PACKET_BUFF_MAX 4096		// �⺻ ��Ŷ ũ��

#define PACKET_HEAD 1000			// ��Ŷ ����
#define PACKET_FILE_START 1100		// ���� ��û
#define PACKET_TAIL 2000			// ��Ŷ ��
#define PACKET_OK 5000				// ó�� �Ϸ�