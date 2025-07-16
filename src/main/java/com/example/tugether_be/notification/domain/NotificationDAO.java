package com.example.tugether_be.notification.domain;

import com.example.tugether_be.post.domain.PostDAO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId; // 알림 고유 ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private PostDAO post; // 관련 게시글

    @Column(nullable = false)
    private Long receiverId; // 알림 수신자 ID

    @Column(nullable = false)
    private String type; // 알림 타입 (JOIN_REQUEST, APPROVED, REJECTED)

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content; // 알림 메시지 (내용)

    @Column(nullable = false)
    private Boolean isRead; // 읽음 여부

    @CreationTimestamp
    private LocalDateTime createdAt; // 생성일시
}
