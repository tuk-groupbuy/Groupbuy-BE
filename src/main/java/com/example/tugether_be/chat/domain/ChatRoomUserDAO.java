package com.example.tugether_be.chat.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ChatRoomUserDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long chatRoomUserId;

    Long chatRoomId;
    Long userId;

    LocalDateTime joinAt;
}
