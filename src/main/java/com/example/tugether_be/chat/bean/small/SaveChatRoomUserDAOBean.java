package com.example.tugether_be.chat.bean.small;

import com.example.tugether_be.chat.domain.ChatRoomUserDAO;
import com.example.tugether_be.chat.repository.ChatRoomUserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveChatRoomUserDAOBean {

    ChatRoomUserRepositoryJPA chatRoomUserRepositoryJPA;

    @Autowired
    public SaveChatRoomUserDAOBean(ChatRoomUserRepositoryJPA chatRoomUserRepositoryJPA) {
        this.chatRoomUserRepositoryJPA = chatRoomUserRepositoryJPA;
    }

    // 채팅방에 입장하는 유저 DAO 저장
    public void exec(ChatRoomUserDAO chatRoomUserDAO){
        chatRoomUserRepositoryJPA.save(chatRoomUserDAO);
    }
}
