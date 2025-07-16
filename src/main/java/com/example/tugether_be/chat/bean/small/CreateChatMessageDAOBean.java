package com.example.tugether_be.chat.bean.small;

import com.example.tugether_be.chat.domain.ChatMessageDAO;
import com.example.tugether_be.chat.domain.DTO.ChatMessageDTO;
import org.springframework.stereotype.Component;

@Component
public class CreateChatMessageDAOBean {

    public ChatMessageDAO exec(ChatMessageDTO chatMessageDTO){
        return ChatMessageDAO.builder()
                .chatRoomId(chatMessageDTO.getChatRoomId())
                .userId(chatMessageDTO.getUserId())
                .senderName(chatMessageDTO.getSender())
                .content(chatMessageDTO.getContent())
                .sendAt(chatMessageDTO.getSendAt())
                .build();
    }
}
