package com.example.tugether_be.notification.bean;

import com.example.tugether_be.notification.domain.NotificationDAO;
import com.example.tugether_be.notification.repository.NotificationRepositoryJPA;
import com.example.tugether_be.post.domain.PostDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateNotificationBean {

    private final NotificationRepositoryJPA notificationRepository;

    public void createJoinRequestNotification(PostDAO post, Long requesterId) {
        // 알림 엔티티 생성
        NotificationDAO notification = NotificationDAO.builder()
                .post(post)
                .receiverId(post.getWriterId()) // 게시글 작성자가 수신자
                .type("JOIN_REQUEST")           // 참여 요청 타입
                .content("게시글 '" + post.getTitle() + "'에 참여 요청이 도착했습니다.") // 알림 내용
                .isRead(false)
                .build();

        notificationRepository.save(notification);
    }
}
