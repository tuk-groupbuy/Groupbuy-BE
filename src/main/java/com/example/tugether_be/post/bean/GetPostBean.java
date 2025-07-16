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

    // 게시글 전체 목록 조회
    public List<ResponsePostSummaryDTO> exec() {
        List<PostDAO> posts = postRepository.findAllByIsDeletedFalse();

        return posts.stream().map(post -> {
            String deadlineText;
            if (post.getDeadline() != null) {
                long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), post.getDeadline().toLocalDate());
                deadlineText = daysLeft >= 0 ? "마감 " + daysLeft + "일 전" : "마감 종료";
            } else {
                deadlineText = "마감일 미설정";
            }

            return ResponsePostSummaryDTO.builder()
                    .postId(post.getPostId())
                    .title(post.getTitle())
                    .imageUrl(post.getImageUrl())
                    .currentQuantity(post.getCurrentParticipants())
                    .goalQuantity(post.getMaxParticipants())
                    .isCompleted(post.getIsCompleted())
                    .price(post.getPrice())
                    .deadlineText(deadlineText)
                    .build();
        }).toList();
    }


    // 게시글 상세 조회
    public ResponsePostDetailDTO execDetail(Long postId) {
        PostDAO post = postRepository.findByPostIdAndIsDeletedFalse(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        LocalDate today = LocalDate.now();
        long daysLeft = ChronoUnit.DAYS.between(today, post.getDeadline().toLocalDate());
        String deadlineText = daysLeft >= 0 ? "마감 " + daysLeft + "일 전" : "마감 종료";

        return ResponsePostDetailDTO.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .content(post.getContent())
                .goalQuantity(post.getMaxParticipants())
                .price(post.getPrice())
                .imageUrl(post.getImageUrl())
                .currentQuantity(post.getCurrentParticipants())
                .isCompleted(post.getIsCompleted())
                .createdAt(post.getCreatedAt().toString())
                .deadlineText(deadlineText)
                .writerName("작성자 이름")
                .build();
    }
}
