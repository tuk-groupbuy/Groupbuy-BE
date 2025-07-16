package com.example.tugether_be.chat.bean.small;

import com.example.tugether_be.chat.domain.ChatRoomUserDAO;
import com.example.tugether_be.chat.repository.ChatRoomUserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class GetChatRoomUserDAOBean {

    ChatRoomUserRepositoryJPA chatRoomUserRepositoryJPA;

    @Autowired
    public GetChatRoomUserDAOBean(ChatRoomUserRepositoryJPA chatRoomUserRepositoryJPA) {
        this.chatRoomUserRepositoryJPA = chatRoomUserRepositoryJPA;
    }

    public ChatRoomUserDAO exec(Long chatRoomId, Long userId){
        return chatRoomUserRepositoryJPA.findByChatRoomIdAndUserId(chatRoomId, userId);
    }

    public List<ChatRoomUserDAO> exec(Long chatRoomId){
        return chatRoomUserRepositoryJPA.findAllByChatRoomId(chatRoomId);
    }
}
