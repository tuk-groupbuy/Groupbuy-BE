package com.example.tugether_be.chat.bean.small;

import com.example.tugether_be.chat.domain.ChatMessageDAO;
import com.example.tugether_be.chat.domain.DTO.ResponseChatMessageGetDTO;
import org.springframework.stereotype.Component;

@Component
public class CreateChatMessageDTOBean {

    public ResponseChatMessageGetDTO exec(ChatMessageDAO chatMessageDAO){
        return ResponseChatMessageGetDTO.builder()
                .sender(chatMessageDAO.getSenderName())
                .content(chatMessageDAO.getContent())
                .sendAt(chatMessageDAO.getSendAt())
                .build();
    }
}
