package com.example.tugether_be.post.domain.DTO;

import com.example.tugether_be.post.domain.type.ParticipationStatus;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
@Builder
public class ResponsePostDetailDTO {
    private Long postId; // 게시글 고유 ID
    private String title; // 게시글 제목
    private String content; // 게시글 내용
    private int goalQuantity; // 목표 모집 인원
    private int price; // 가격
    private String imageUrl; // 이미지 경로
    private int currentQuantity; // 현재 모집 인원
    private int minParticipant; // 최소 모집 인원
    private boolean isCompleted; // 거래 완료 여부
    private String createdAt; // 게시글 생성일
    private String writerName; // 작성자 이름
    private Long writerId; // 작성자 ID
    private LocalDateTime deadline; // 마감일
    private Boolean isWriter;
    private String participationStatus; // 참여자 상태 (신청 전, 승인 전, 승인 후)
}
