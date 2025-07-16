package com.example.tugether_be.chat.bean.small;

import com.example.tugether_be.chat.domain.ChatMessageDAO;
import com.example.tugether_be.chat.domain.DTO.RequestChatRoomsGetDTO;
import com.example.tugether_be.chat.repository.ChatMessageRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetChatMessageDAOBean {

    ChatMessageRepositoryJPA chatMessageRepositoryJPA;

    @Autowired
    public GetChatMessageDAOBean(ChatMessageRepositoryJPA chatMessageRepositoryJPA) {
        this.chatMessageRepositoryJPA = chatMessageRepositoryJPA;
    }

    public ChatMessageDAO exec(Long chatRoomId){
        return chatMessageRepositoryJPA.findTop1ByChatRoomIdOrderBySendAtDesc(chatRoomId);
    }
}