package com.example.tugether_be.post.bean;

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
public class SearchPostBean {

    private final PostRepositoryJPA postRepositoryJPA;

    public List<ResponsePostSummaryDTO> exec(String keyword) {
        List<PostDAO> posts = postRepositoryJPA.searchByTitleLike(keyword);

        return posts.stream()
                .map(post -> {
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
                })
                .toList();
    }
}
