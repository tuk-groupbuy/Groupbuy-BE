package com.example.tugether_be.chat.bean;

import com.example.tugether_be.chat.bean.small.CreateChatMessageDAOBean;
import com.example.tugether_be.chat.bean.small.SaveChatMessageDAOBean;
import com.example.tugether_be.chat.domain.ChatMessageDAO;
import com.example.tugether_be.chat.domain.DTO.ChatMessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveChatMessageBean {

    CreateChatMessageDAOBean createChatMessageDAOBean;
    SaveChatMessageDAOBean saveChatMessageDAOBean;

    @Autowired
    public SaveChatMessageBean(CreateChatMessageDAOBean createChatMessageDAOBean, SaveChatMessageDAOBean saveChatMessageDAOBean) {
        this.createChatMessageDAOBean = createChatMessageDAOBean;
        this.saveChatMessageDAOBean = saveChatMessageDAOBean;
    }

    // 채팅 저장
    public void exec(ChatMessageDTO chatMessageDTO){
        // 채팅 메시지 DAO 생성
        ChatMessageDAO chatMessageDAO = createChatMessageDAOBean.exec(chatMessageDTO);
        if (chatMessageDAO == null) return;

        // DAO 저장
        saveChatMessageDAOBean.exec(chatMessageDAO);
    }
}
