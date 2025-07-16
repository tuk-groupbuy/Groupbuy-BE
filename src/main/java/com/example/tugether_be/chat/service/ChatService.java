package com.example.tugether_be.chat.service;

import com.example.tugether_be.chat.bean.SaveChatMessageBean;
import com.example.tugether_be.chat.domain.DTO.ChatMessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    SaveChatMessageBean saveChatMessageBean;

    @Autowired
    public ChatService(SaveChatMessageBean saveChatMessageBean){
        this.saveChatMessageBean = saveChatMessageBean;
    }

    // 채팅 DB 저장
    public void saveMessage(ChatMessageDTO chatMessageDTO) {
        saveChatMessageBean.exec(chatMessageDTO);
    }
}
