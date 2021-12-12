#pragma once

// 사용자 메시지
#define WM_CAPTURE_SCREEN WM_USER + 100		// 화면 캡처 메시지
#define WM_SEND_DATA WM_USER + 101			// 파일 송신 메시지
#define WM_SOCKET_CLOSED WM_USER + 102		// 파일 송신 메시지

// 타미머 설정 값
#define TIMER_SCREEN 1					// 번호 1
#define TIMER_SEND 2					// 번호 2
#define TIMER_RECONNECT 3				// 번호 3
#define TIMER_INTERVAL_SCREEN 1000		// 인터벌 1
#define TIMER_INTERVAL_SEND 5000		// 인터벌 2
#define TIMER_INTERVAL_RECONNECT 5000	// 인터벌 3
//#define TIMER_INTERVAL_RECONNECT 300000	// 인터벌 3

// 서버 접속 정보
#define SERVER_ADDR "192.168.11.10"		// 아이피
//#define SERVER_ADDR "127.0.0.1"		// 아이피
#define SERVER_PORT 10002			// 포트

// 패킷 기본설정
#define PACKET_BUFF_MAX 4096		// 기본 패킷 크기

#define PACKET_HEAD 1000			// 패킷 시작
#define PACKET_FILE_START 1100		// 파일 요청
#define PACKET_TAIL 2000			// 패킷 끝
#define PACKET_OK 5000				// 처리 완료
