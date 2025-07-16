package com.example.tugether_be.chat.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ChatMessageDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long chatMessageId;

    Long chatRoomId;
    Long userId; // 보내는 사람

    String senderName;
    String content;

    LocalDateTime sendAt;
}
