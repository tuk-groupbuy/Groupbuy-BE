package com.example.tugether_be.chat.domain.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class RequestChatRoomUserSaveDTO {
    Long userId;
    Long chatRoomId;
}
