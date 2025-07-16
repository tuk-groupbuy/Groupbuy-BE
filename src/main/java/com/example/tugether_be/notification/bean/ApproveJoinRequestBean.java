package com.example.tugether_be.notification.bean;

import com.example.tugether_be.notification.domain.DTO.RequestJoinApprovalDTO;
import com.example.tugether_be.notification.repository.NotificationRepositoryJPA;
import com.example.tugether_be.post.repository.JoinPostRepositoryJPA;
import com.example.tugether_be.post.domain.JoinPostDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApproveJoinRequestBean {

    private final JoinPostRepositoryJPA joinPostRepository;
    private final NotificationRepositoryJPA notificationRepository;

    public void exec(RequestJoinApprovalDTO dto) {
        JoinPostDAO joinPost = joinPostRepository.findByPost_PostIdAndUserId(dto.getPostId(), dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 참여 요청이 존재하지 않습니다."));

        joinPost.setApproved(true);
        joinPostRepository.save(joinPost);
    }
}
