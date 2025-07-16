package com.example.tugether_be.chat.bean.small;

import com.example.tugether_be.chat.domain.ChatRoomDAO;
import com.example.tugether_be.chat.repository.ChatRoomRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveChatRoomDAOBean {

    ChatRoomRepositoryJPA chatRoomRepositoryJPA;

    @Autowired
    public SaveChatRoomDAOBean(ChatRoomRepositoryJPA chatRoomRepositoryJPA) {
        this.chatRoomRepositoryJPA = chatRoomRepositoryJPA;
    }

    // 채팅방 DAO 저장
    public void exec(ChatRoomDAO chatRoomDAO){
        chatRoomRepositoryJPA.save(chatRoomDAO);
    }
}
