#pragma once

// 사용자 메시지
#define WM_UPDATE_LOG WM_USER + 100
#define WM_CLIENT_CLOSED WM_USER + 101

// 서버 정보
#define SERVER_PORT 10002	// 리슨 포트

// 패킷 기본설정
#define PACKET_BUFF_MAX 4096		// 기본 패킷 크기

#define PACKET_HEAD 1000			// 패킷 시작
#define PACKET_FILE_START 1100		// 파일 요청
#define PACKET_TAIL 2000			// 패킷 끝
#define PACKET_OK 5000				// 처리 완료