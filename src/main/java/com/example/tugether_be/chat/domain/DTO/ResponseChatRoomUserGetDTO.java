package com.example.tugether_be.chat.domain.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseChatRoomUserGetDTO {
    Long userId;
    boolean isOwner;
}
