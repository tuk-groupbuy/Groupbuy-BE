package com.example.tugether_be.post.bean;

import com.example.tugether_be.notification.bean.CreateNotificationBean; // ✅ 알림 생성 빈 추가
import com.example.tugether_be.post.domain.PostDAO;
import com.example.tugether_be.post.domain.JoinPostDAO;
import com.example.tugether_be.post.domain.DTO.RequestJoinPostDTO;
import com.example.tugether_be.post.repository.JoinPostRepositoryJPA;
import com.example.tugether_be.post.repository.PostRepositoryJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.example.tugether_be.post.domain.type.ParticipationStatus.REQUESTED;

@Component
@RequiredArgsConstructor
public class JoinPostBean {

    private final PostRepositoryJPA postRepository;
    private final JoinPostRepositoryJPA joinPostRepository;
    private final CreateNotificationBean createNotificationBean;

    public void exec(RequestJoinPostDTO dto) {
        // 1. 게시글 조회
        PostDAO post = postRepository.findById(dto.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        // 2. 모집 완료 여부 체크
        if (Boolean.TRUE.equals(post.getIsCompleted())) {
            throw new IllegalStateException("이미 모집이 완료된 게시글입니다.");
        }

        // 3. 중복 참여 방지
        Optional<JoinPostDAO> existing = joinPostRepository.findByPost_PostIdAndUserId(dto.getPostId(), dto.getUserId());
        if (existing.isPresent()) {
            throw new IllegalStateException("이미 해당 게시글에 참여한 사용자입니다.");
        }

        // 4. 참여 정보 저장
        JoinPostDAO joinPost = JoinPostDAO.builder()
                .post(post)
                .userId(dto.getUserId())
                .build();
        joinPostRepository.save(joinPost);

        // + 알림 생성
        createNotificationBean.createJoinRequestNotification(post, dto.getUserId());

        // 5. 현재 참여 인원 증가
        int updated = post.getCurrentParticipants() == null ? 1 : post.getCurrentParticipants() + 1;
        post.setCurrentParticipants(updated);

        // 6. 최대 참여 인원 도달 → 거래 완료 처리
        if (updated >= post.getMaxParticipants()) {
            post.setIsCompleted(true);
        }

        post.setParticipationStatus(REQUESTED);

        // 7. 게시글 갱신
        postRepository.save(post);
    }
}
