package com.example.tugether_be.chat.domain.DTO;

import lombok.Data;

import java.util.List;

@Data
public class ResponseChatMessagePageGetDTO {

    private List<ResponseChatMessageGetDTO> responseChatMessageGetDTOS;
    private boolean isLast;

    public ResponseChatMessagePageGetDTO(List<ResponseChatMessageGetDTO> responseChatMessageGetDTOS, boolean isLast) {
        this.responseChatMessageGetDTOS = responseChatMessageGetDTOS;
        this.isLast = isLast;
    }

}
