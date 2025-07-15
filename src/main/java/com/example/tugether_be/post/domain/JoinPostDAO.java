package com.example.tugether_be.post.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "join_post")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JoinPostDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long joinPostId; // 참여 고유 ID (PK)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private PostDAO post;    // 참여 대상 게시글

    private Long userId;     // 참여한 사용자 ID

    private Boolean approved; // 참여 요청 승인 여부


    @CreationTimestamp
    private LocalDateTime joinedAt; // 참여 시간 (자동 생성)
}
