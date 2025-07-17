package com.example.tugether_be.post.bean;

import com.example.tugether_be.post.domain.DTO.ResponsePostSummaryDTO;
import com.example.tugether_be.post.domain.PostDAO;
import com.example.tugether_be.post.repository.PostRepositoryJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SearchPostBean {

    private final PostRepositoryJPA postRepositoryJPA;

    public List<ResponsePostSummaryDTO> exec(String keyword) {
        List<PostDAO> posts = postRepositoryJPA.searchByTitleLike(keyword);

        return posts.stream()
                .map(post -> ResponsePostSummaryDTO.builder()
                        .postId(post.getPostId())
                        .title(post.getTitle())
                        .imageUrl(post.getImageUrl())
                        .currentQuantity(post.getCurrentParticipants())
                        .goalQuantity(post.getMaxParticipants())
                        .build()
                ).toList();
    }

    /*
    private String formatDeadline(LocalDate deadline) {
        long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), deadline);
        return "마감 " + daysLeft + "일 전";
    }
    */
}
