package com.example.tugether_be.chat.bean.small;

import com.example.tugether_be.chat.domain.ChatMessageDAO;
import com.example.tugether_be.chat.repository.ChatMessageRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveChatMessageDAOBean {

    ChatMessageRepositoryJPA chatMessageRepositoryJPA;

    @Autowired
    public SaveChatMessageDAOBean(ChatMessageRepositoryJPA chatMessageRepositoryJPA) {
        this.chatMessageRepositoryJPA = chatMessageRepositoryJPA;
    }

    // DAO 저장
    public void exec(ChatMessageDAO chatMessageDAO){
        chatMessageRepositoryJPA.save(chatMessageDAO);
    }
}
