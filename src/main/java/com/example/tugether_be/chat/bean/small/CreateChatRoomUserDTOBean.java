package com.example.tugether_be.chat.bean.small;

import com.example.tugether_be.chat.domain.ChatRoomDAO;
import com.example.tugether_be.chat.domain.ChatRoomUserDAO;
import com.example.tugether_be.chat.domain.DTO.ResponseChatRoomUserGetDTO;
import org.springframework.stereotype.Component;

@Component
public class CreateChatRoomUserDTOBean {

    public ResponseChatRoomUserGetDTO exec(ChatRoomUserDAO chatRoomUserDAO, ChatRoomDAO chatRoomDAO){
        return ResponseChatRoomUserGetDTO.builder()
                .userId(chatRoomUserDAO.getUserId())
                .isOwner(chatRoomUserDAO.getUserId().equals(chatRoomDAO.getOwnerId()))
                .nickname(chatRoomUserDAO.getNickname())
                .build();
    }
}
