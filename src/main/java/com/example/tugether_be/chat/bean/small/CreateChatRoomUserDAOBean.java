package com.example.tugether_be.chat.bean.small;

import com.example.tugether_be.chat.domain.ChatRoomUserDAO;
import com.example.tugether_be.chat.domain.DTO.RequestChatRoomSaveDTO;
import com.example.tugether_be.chat.domain.DTO.RequestChatRoomUserSaveDTO;
import com.example.tugether_be.chat.repository.ChatRoomUserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class CreateChatRoomUserDAOBean {

    ChatRoomUserRepositoryJPA chatRoomUserRepositoryJPA;

    @Autowired
    public CreateChatRoomUserDAOBean(ChatRoomUserRepositoryJPA chatRoomUserRepositoryJPA) {
        this.chatRoomUserRepositoryJPA = chatRoomUserRepositoryJPA;
    }

    // 생성되어 있는 채팅방에 입장하는 DAO 생성
    public ChatRoomUserDAO exec(RequestChatRoomUserSaveDTO requestChatRoomUserSaveDTO){

        boolean exists = chatRoomUserRepositoryJPA.existsByUserIdAndChatRoomId(requestChatRoomUserSaveDTO.getUserId(), requestChatRoomUserSaveDTO.getChatRoomId());
        if (!exists) {
            return ChatRoomUserDAO.builder()
                    .chatRoomId(requestChatRoomUserSaveDTO.getChatRoomId())
                    .userId(requestChatRoomUserSaveDTO.getUserId())
                    .joinAt(LocalDateTime.now())
                    .build();
            }
        else return null;
    }

    // 처음 채팅방을 생성하는 유저 입장하는 DAO 생성
    public ChatRoomUserDAO exec(Long chatRoomId, RequestChatRoomSaveDTO requestChatRoomSaveDTO, LocalDateTime now){
        return ChatRoomUserDAO.builder()
                .chatRoomId(chatRoomId)
                .userId(requestChatRoomSaveDTO.getUserId())
                .joinAt(now)
                .build();
    }
}
