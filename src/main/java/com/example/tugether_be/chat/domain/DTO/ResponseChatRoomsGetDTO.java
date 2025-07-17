package com.example.tugether_be.chat.domain.DTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ResponseChatRoomsGetDTO {
    Long chatRoomId;

    String title;
    String imageUrl;

    String lastChatMessage;

    Boolean isRead;

    LocalDateTime sendAt;
}