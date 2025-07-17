package com.example.tugether_be.notification.domain.DTO;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseNotificationDTO {

    private Long notificationId; // 알림 ID
    private String type;         // 알림 타입 (JOIN_REQUEST, APPROVED, REJECTED)
    private String content;      // 알림 내용
    private Boolean isRead;      // 읽음 여부
    private String createdAt;    // 알림 생성 시간 (문자열로 반환)
    private Long userId; // 참여 요청한 사용자 ID
    private Integer currentQuantity; // 현재 인원
    private Integer goalQuantity; // 최대 인원
    private String senderNickname;
}