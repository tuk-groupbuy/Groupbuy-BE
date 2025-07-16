package com.example.tugether_be.post.bean;

import com.example.tugether_be.post.domain.DTO.RequestPostUpdateDTO;
import com.example.tugether_be.post.domain.PostDAO;
import com.example.tugether_be.post.repository.PostRepositoryJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdatePostBean {

    private final PostRepositoryJPA postRepository;

    public void exec(Long postId, RequestPostUpdateDTO dto) {
        PostDAO post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setPrice(dto.getPrice());
        post.setMaxParticipants(dto.getGoalQuantity());
        post.setImageUrl(dto.getImageUrl());

        postRepository.save(post);
    }
}
