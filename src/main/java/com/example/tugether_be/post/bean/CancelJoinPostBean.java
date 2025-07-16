package com.example.tugether_be.post.bean;

import com.example.tugether_be.post.domain.PostDAO;
import com.example.tugether_be.post.repository.JoinPostRepositoryJPA;
import com.example.tugether_be.post.repository.PostRepositoryJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CancelJoinPostBean {

    private final PostRepositoryJPA postRepository;
    private final JoinPostRepositoryJPA joinPostRepository;

    public void exec(Long postId, Long userId) {
        // 1. 참여 정보 삭제
        joinPostRepository.deleteByPost_PostIdAndUserId(postId, userId);

        // 2. 게시글 현재 참여 인원 1 감소
        PostDAO post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        int current = post.getCurrentParticipants() == null ? 0 : post.getCurrentParticipants();
        post.setCurrentParticipants(Math.max(current - 1, 0));

        // 3. 최대 인원 미만이면 거래 완료 해제
        if (Boolean.TRUE.equals(post.getIsCompleted()) && post.getCurrentParticipants() < post.getMaxParticipants()) {
            post.setIsCompleted(false);
        }

        // 4. 저장
        postRepository.save(post);
    }
}
