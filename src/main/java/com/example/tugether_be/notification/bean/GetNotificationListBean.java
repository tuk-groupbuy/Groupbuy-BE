package com.example.tugether_be.notification.bean;

import com.example.tugether_be.auth.entity.User;
import com.example.tugether_be.auth.repository.UserRepository;
import com.example.tugether_be.notification.domain.DTO.ResponseNotificationDTO;
import com.example.tugether_be.notification.domain.NotificationDAO;
import com.example.tugether_be.notification.repository.NotificationRepositoryJPA;
import com.example.tugether_be.post.domain.PostDAO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Transactional
public class GetNotificationListBean {

    private final NotificationRepositoryJPA notificationRepository;
    private final UserRepository userRepository;

    public List<ResponseNotificationDTO> exec(Long userId) {
        List<NotificationDAO> notifications = notificationRepository.findByReceiverIdOrderByCreatedAtDesc(userId);

        return notifications.stream().map(notification -> {
            PostDAO post = notification.getPost();

            Long senderId = notification.getSenderId();  // NotificationDAO에 senderId가 있어야 함
            String senderNickname = userRepository.findById(senderId)
                    .map(User::getNickname)
                    .orElse("알 수 없음");

            return ResponseNotificationDTO.builder()
                    .postId(post != null ? post.getPostId() : null)
                    .notificationId(notification.getNotificationId())
                    .content(notification.getContent())
                    .type(notification.getType())
                    .isRead(notification.getIsRead())
                    .createdAt(notification.getCreatedAt().toString())
                    .userId(senderId)
                    .currentQuantity(post != null ? post.getCurrentParticipants() : null)
                    .goalQuantity(post != null ? post.getMaxParticipants() : null)
                    .senderNickname(senderNickname)
                    .build();
        }).toList();
    }
}
