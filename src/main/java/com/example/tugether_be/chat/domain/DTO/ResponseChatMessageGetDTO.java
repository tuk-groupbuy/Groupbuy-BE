package com.example.tugether_be.chat.domain.DTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ResponseChatMessageGetDTO {
    String sender;
    String content;

    LocalDateTime sendAt;
}
