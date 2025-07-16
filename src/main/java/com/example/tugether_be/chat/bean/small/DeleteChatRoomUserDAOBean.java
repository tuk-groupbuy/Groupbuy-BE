package com.example.tugether_be.chat.bean.small;

import com.example.tugether_be.chat.domain.ChatRoomUserDAO;
import com.example.tugether_be.chat.repository.ChatRoomUserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteChatRoomUserDAOBean {

    ChatRoomUserRepositoryJPA chatRoomUserRepositoryJPA;

    @Autowired
    public DeleteChatRoomUserDAOBean(ChatRoomUserRepositoryJPA chatRoomUserRepositoryJPA) {
        this.chatRoomUserRepositoryJPA = chatRoomUserRepositoryJPA;
    }

    public void exec(ChatRoomUserDAO chatRoomUserDAO){
        chatRoomUserRepositoryJPA.delete(chatRoomUserDAO);
    }
}
