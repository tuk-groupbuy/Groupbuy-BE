package com.example.tugether_be.chat.domain.DTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class ChatMessageDTO {
    String sender;
    String content;

    Long userId;
    Long chatRoomId;

    LocalDateTime sendAt;
}
