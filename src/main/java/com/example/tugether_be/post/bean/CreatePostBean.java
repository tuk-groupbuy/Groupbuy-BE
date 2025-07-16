package com.example.tugether_be.post.bean;

import com.example.tugether_be.post.domain.DTO.RequestPostCreateDTO;
import com.example.tugether_be.post.domain.PostDAO;
import com.example.tugether_be.post.repository.PostRepositoryJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreatePostBean {

    private final PostRepositoryJPA postRepository;

    // imageUrl 매개변수 추가
    public Long exec(RequestPostCreateDTO dto, String imageUrl) {
        PostDAO post = PostDAO.builder()
                .writerId(dto.getWriterId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .maxParticipants(dto.getGoalQuantity())
                .price(dto.getPricePerOne())
                .imageUrl(imageUrl) // 저장된 파일 경로 사용
                .currentParticipants(0)
                .isCompleted(false)
                .isDeleted(false)
                .build();

        postRepository.save(post);
        return post.getPostId();
    }
}
