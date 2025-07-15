package com.example.tugether_be.post.bean;

import com.example.tugether_be.post.domain.PostDAO;
import com.example.tugether_be.post.repository.PostRepositoryJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeletePostBean {

    private final PostRepositoryJPA postRepository;

    public void exec(Long postId) {
        PostDAO post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        post.setIsDeleted(true);
        postRepository.save(post);
    }
}
