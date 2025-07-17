package com.example.tugether_be.notification.bean;

import com.example.tugether_be.notification.domain.DTO.ResponseNotificationDTO;
import com.example.tugether_be.notification.domain.NotificationDAO;
import com.example.tugether_be.notification.repository.NotificationRepositoryJPA;
import com.example.tugether_be.post.domain.PostDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetNotificationListBean {

    private final NotificationRepositoryJPA notificationRepository;

    public List<ResponseNotificationDTO> exec(Long userId) {
        List<NotificationDAO> notifications = notificationRepository.findByReceiverIdOrderByCreatedAtDesc(userId);

        return notifications.stream().map(notification -> {
            PostDAO post = notification.getPost();

            return ResponseNotificationDTO.builder()
                    .notificationId(notification.getNotificationId())
                    .content(notification.getContent())
                    .type(notification.getType())
                    .isRead(notification.getIsRead())
                    .createdAt(notification.getCreatedAt().toString())
                    .currentQuantity(post != null ? post.getCurrentParticipants() : null)
                    .goalQuantity(post != null ? post.getMaxParticipants() : null)
                    .build();
        }).toList();
    }
}
