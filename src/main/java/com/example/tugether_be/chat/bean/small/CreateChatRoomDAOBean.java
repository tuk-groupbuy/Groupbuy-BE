package com.example.tugether_be.chat.bean.small;

import com.example.tugether_be.chat.domain.ChatRoomDAO;
import com.example.tugether_be.chat.domain.DTO.RequestChatRoomSaveDTO;
import com.example.tugether_be.chat.repository.ChatRoomRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class CreateChatRoomDAOBean {

    ChatRoomRepositoryJPA chatRoomRepositoryJPA;

    @Autowired
    public CreateChatRoomDAOBean(ChatRoomRepositoryJPA chatRoomRepositoryJPA) {
        this.chatRoomRepositoryJPA = chatRoomRepositoryJPA;
    }

    // 채팅방 DAO 생성
    public ChatRoomDAO exec(RequestChatRoomSaveDTO requestChatRoomSaveDTO, LocalDateTime now){
        return ChatRoomDAO.builder()
                .ownerId(requestChatRoomSaveDTO.getUserId())
                .createAt(now)
                .build();
    }
}
