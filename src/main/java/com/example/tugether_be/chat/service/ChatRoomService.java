package com.example.tugether_be.chat.service;

import com.example.tugether_be.chat.bean.CreateChatRoomBean;
import com.example.tugether_be.chat.bean.GetChatRoomsBean;
import com.example.tugether_be.chat.domain.DTO.RequestChatRoomSaveDTO;
import com.example.tugether_be.chat.domain.DTO.RequestChatRoomsGetDTO;
import com.example.tugether_be.chat.domain.DTO.ResponseChatRoomsGetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ChatRoomService {

    CreateChatRoomBean createChatRoomBean;
    GetChatRoomsBean getChatRoomsBean;

    @Autowired
    public ChatRoomService(CreateChatRoomBean createChatRoomBean, GetChatRoomsBean getChatRoomsBean) {
        this.createChatRoomBean = createChatRoomBean;
        this.getChatRoomsBean = getChatRoomsBean;
    }

    // 채팅방 생성
    public Long createChatRoom(RequestChatRoomSaveDTO requestChatRoomSaveDTO){
        return createChatRoomBean.exec(requestChatRoomSaveDTO);
    }

    // 채팅방 목록 조회
    public List<ResponseChatRoomsGetDTO> getChatRooms(Long userId){
        return getChatRoomsBean.exec(userId);
    }
}