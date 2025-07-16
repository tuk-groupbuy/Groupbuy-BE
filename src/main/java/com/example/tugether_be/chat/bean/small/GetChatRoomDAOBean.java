package com.example.tugether_be.chat.bean.small;

import com.example.tugether_be.chat.domain.ChatRoomDAO;
import com.example.tugether_be.chat.repository.ChatRoomRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetChatRoomDAOBean {

    ChatRoomRepositoryJPA chatRoomRepositoryJPA;

    @Autowired
    public GetChatRoomDAOBean(ChatRoomRepositoryJPA chatRoomRepositoryJPA) {
        this.chatRoomRepositoryJPA = chatRoomRepositoryJPA;
    }

    public ChatRoomDAO exec(Long chatRoomId){
        return chatRoomRepositoryJPA.findById(chatRoomId).orElse(null);
    }
}
