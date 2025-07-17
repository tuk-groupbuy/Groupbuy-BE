package com.example.tugether_be.chat.domain.DTO;

import lombok.Data;

@Data
public class RequestChatRoomSaveDTO {
    Long userId;
    Long postId;
}
