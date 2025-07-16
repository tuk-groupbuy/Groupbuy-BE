package com.example.tugether_be.post.domain.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class ResponsePostDetailDTO {
    private Long postId; // 게시글 고유 ID
    private String title; // 게시글 제목
    private String content; // 게시글 내용
    private int goalQuantity; // 목표 모집 인원
    private int pricePerOne; // 1인당 가격
    private String imageUrl; // 이미지 경로
    private int currentQuantity; // 현재 모집 인원
    private boolean isCompleted; // 거래 완료 여부
    private String createdAt; // 게시글 생성일
    private String writerName; // 작성자 이름
}
