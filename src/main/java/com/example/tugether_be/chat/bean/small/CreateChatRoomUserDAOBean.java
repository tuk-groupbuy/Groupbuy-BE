package com.example.tugether_be.chat.bean.small;

import com.example.tugether_be.auth.entity.User;
import com.example.tugether_be.auth.repository.UserRepository;
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
    UserRepository userRepository;

    @Autowired
    public CreateChatRoomUserDAOBean(ChatRoomUserRepositoryJPA chatRoomUserRepositoryJPA, UserRepository userRepository) {
        this.chatRoomUserRepositoryJPA = chatRoomUserRepositoryJPA;
        this.userRepository = userRepository;
    }

    // 생성되어 있는 채팅방에 입장하는 DAO 생성
    public ChatRoomUserDAO exec(RequestChatRoomUserSaveDTO requestChatRoomUserSaveDTO){

        User user = userRepository.findById(requestChatRoomUserSaveDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        boolean exists = chatRoomUserRepositoryJPA.existsByUserIdAndChatRoomId(requestChatRoomUserSaveDTO.getUserId(), requestChatRoomUserSaveDTO.getChatRoomId());
        if (!exists) {
            return ChatRoomUserDAO.builder()
                    .chatRoomId(requestChatRoomUserSaveDTO.getChatRoomId())
                    .userId(requestChatRoomUserSaveDTO.getUserId())
                    .nickname(user.getNickname())
                    .joinAt(LocalDateTime.now())
                    .build();
            }
        else return null;
    }

    // 처음 채팅방을 생성하는 유저 입장하는 DAO 생성
    public ChatRoomUserDAO exec(Long chatRoomId, RequestChatRoomSaveDTO requestChatRoomSaveDTO, LocalDateTime now){

        User user = userRepository.findById(requestChatRoomSaveDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        return ChatRoomUserDAO.builder()
                .chatRoomId(chatRoomId)
                .userId(requestChatRoomSaveDTO.getUserId())
                .nickname(user.getNickname())
                .joinAt(now)
                .build();
    }
}
