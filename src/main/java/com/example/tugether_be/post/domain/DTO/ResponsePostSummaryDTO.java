package com.example.tugether_be.post.domain.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
@Builder
public class ResponsePostSummaryDTO {
    private Long postId;            // 게시글 고유 ID
    private String title;           // 게시글 제목
    private String imageUrl;        // 게시글 대표 이미지 URL
    private int currentQuantity;    // 현재 모집 인원
    private int goalQuantity;       // 목표 모집 인원
    private boolean isCompleted;    // 거래 완료 여부
    private int price;        // 가격
    private String deadlineText; // 마감일까지 남은 기간
}
