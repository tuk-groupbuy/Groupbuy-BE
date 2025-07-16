package com.example.tugether_be.chat.service;

import com.example.tugether_be.chat.bean.DeleteChatRoomUserBean;
import com.example.tugether_be.chat.bean.GetChatRoomUsersBean;
import com.example.tugether_be.chat.bean.SaveChatRoomUserBean;
import com.example.tugether_be.chat.domain.DTO.RequestChatRoomUserDeleteDTO;
import com.example.tugether_be.chat.domain.DTO.RequestChatRoomUserSaveDTO;
import com.example.tugether_be.chat.domain.DTO.ResponseChatRoomUserGetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ChatRoomUserService {

    GetChatRoomUsersBean getChatRoomUsersBean;
    SaveChatRoomUserBean saveChatRoomUserBean;
    DeleteChatRoomUserBean deleteChatRoomUserBean;

    @Autowired
    public ChatRoomUserService(GetChatRoomUsersBean getChatRoomUsersBean,SaveChatRoomUserBean saveChatRoomUserBean, DeleteChatRoomUserBean deleteChatRoomUserBean) {
        this.getChatRoomUsersBean = getChatRoomUsersBean;
        this.saveChatRoomUserBean = saveChatRoomUserBean;
        this.deleteChatRoomUserBean = deleteChatRoomUserBean;
    }

    // 채팅방 참여 인원 조회
    public List<ResponseChatRoomUserGetDTO> getChatRoomUsers(Long chatRoomId){
        return getChatRoomUsersBean.exec(chatRoomId);
    }

    // 채팅방 참여
    public Long joinChatRoom(RequestChatRoomUserSaveDTO requestChatRoomUserSaveDTO){
        return saveChatRoomUserBean.exec(requestChatRoomUserSaveDTO);
    }

    // 채팅방 나가기
    public Long leaveChatRoom(RequestChatRoomUserDeleteDTO requestChatRoomUserDeleteDTO){
        return deleteChatRoomUserBean.exec(requestChatRoomUserDeleteDTO);
    }
}
