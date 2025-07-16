package com.example.tugether_be.chat.service;

import com.example.tugether_be.chat.bean.GetChatMessagesBean;
import com.example.tugether_be.chat.domain.DTO.ResponseChatMessageGetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ChatMessageService {

    GetChatMessagesBean getChatMessagesBean;

    @Autowired
    public ChatMessageService(GetChatMessagesBean getChatMessagesBean) {
        this.getChatMessagesBean = getChatMessagesBean;
    }

    // 채팅 내역 가져오기
    public List<ResponseChatMessageGetDTO> getChatMessages(Long roomId, int page, int size){
        return getChatMessagesBean.exec(roomId, page, size);
    }
}
