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

    // requesterId 없이 호출될 때
    public ResponsePostDetailDTO execDetail(Long postId) {
        return execDetail(postId, null); // 아래 메서드 재사용
    }

    // 게시글 상세 조회 (requesterId 포함)
    public ResponsePostDetailDTO execDetail(Long postId, Long requesterId) {
        PostDAO post = postRepository.findByPostIdAndIsDeletedFalse(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        LocalDate today = LocalDate.now();
        long daysLeft = ChronoUnit.DAYS.between(today, post.getDeadline().toLocalDate());
        String deadlineText = daysLeft >= 0 ? "마감 " + daysLeft + "일 전" : "마감 종료";

        // 요청자와 작성자 비교
        boolean isWriter = requesterId != null && post.getWriterId().equals(requesterId);

        // 참여 상태 결정
//        String participationStatus = "None";

        return ResponsePostDetailDTO.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .content(post.getContent())
                .goalQuantity(post.getMaxParticipants())
                .price(post.getPrice())
                .imageUrl(post.getImageUrl())
                .currentQuantity(post.getCurrentParticipants())
                .minParticipant(post.getMinParticipants())
                .isCompleted(post.getIsCompleted())
                .createdAt(post.getCreatedAt().toString())
                .deadline(post.getDeadline())
                .writerName("작성자 이름")
                .isWriter(isWriter)
                .participationStatus(String.valueOf(post.getParticipationStatus()))
                .build();
    }
}
