package com.example.tugether_be.post.bean;

import com.example.tugether_be.post.domain.DTO.ResponsePostDetailDTO;
import com.example.tugether_be.post.domain.DTO.ResponsePostSummaryDTO;
import com.example.tugether_be.post.domain.PostDAO;
import com.example.tugether_be.post.repository.PostRepositoryJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GetPostBean {

    private final PostRepositoryJPA postRepository;

    // 게시글 전체 목록을 조회하여 ResponsePostSummaryDTO 리스트로 변환

    public List<ResponsePostSummaryDTO> exec() {
        List<PostDAO> posts = postRepository.findAllByIsDeletedFalse();

        return posts.stream().map(post -> {
            // 1. 마감일 계산: 생성일 기준 7일 후로 가정
            LocalDate createdDate = post.getCreatedAt().toLocalDate();
            LocalDate deadline = createdDate.plusDays(7);
            LocalDate today = LocalDate.now();

            // 2. 남은 날짜 계산
            long daysLeft = ChronoUnit.DAYS.between(today, deadline);
            String deadlineText = daysLeft >= 0 ? "마감 " + daysLeft + "일 전" : "마감 종료";

            // 3. DTO 생성
            return ResponsePostSummaryDTO.builder()
                    .postId(post.getPostId())
                    .title(post.getTitle())
                    .imageUrl(post.getImageUrl())
                    .currentQuantity(post.getCurrentParticipants())
                    .goalQuantity(post.getMaxParticipants())
                    .isCompleted(post.getIsCompleted())
                    .pricePerOne(post.getPrice())
                    .deadlineText(deadlineText)
                    .build();
        }).toList();
    }

    // 게시글 단건 상세 조회
    public ResponsePostDetailDTO execDetail(Long postId) {
        PostDAO post = postRepository.findByPostIdAndIsDeletedFalse(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        return ResponsePostDetailDTO.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .content(post.getContent())
                .goalQuantity(post.getMaxParticipants())
                .pricePerOne(post.getPrice())
                .imageUrl(post.getImageUrl())
                .currentQuantity(post.getCurrentParticipants())
                .isCompleted(post.getIsCompleted())
                .createdAt(post.getCreatedAt().toString())
                .writerName("작성자 이름") // 실제 구현 시 사용자 이름 조회 로직 필요
                .build();
    }
}
