package com.example.tugether_be.post.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "post")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(nullable = false)
    private String title; // 게시글 제목

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content; // 게시글 내용

    private Integer price; // 가격

    private Integer maxParticipants; // 모집 인원 수

    private Integer currentParticipants; // 현재 모집 인원

    private Integer minParticipants; // 최소 모집 인원

    private Boolean isCompleted; // 모집 마감 여부

    private Boolean isDeleted; // 삭제 여부

    private String imageUrl; // 이미지 URL

    private Long writerId; // 작성자 ID

    private LocalDateTime deadline; // 모집 마감일

    @CreationTimestamp
    private LocalDateTime createdAt; // 생성일시

    @UpdateTimestamp
    private LocalDateTime updatedAt; // 수정일시
}
