package com.example.tugether_be.chat.service;

import com.example.tugether_be.chat.bean.CreateChatRoomBean;
import com.example.tugether_be.chat.domain.DTO.RequestChatRoomSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ChatRoomService {

    CreateChatRoomBean createChatRoomBean;

    @Autowired
    public ChatRoomService(CreateChatRoomBean createChatRoomBean) {
        this.createChatRoomBean = createChatRoomBean;
    }

    // 채팅방 생성
    public Long createChatRoom(RequestChatRoomSaveDTO requestChatRoomSaveDTO){
        return createChatRoomBean.exec(requestChatRoomSaveDTO);
    }
}
