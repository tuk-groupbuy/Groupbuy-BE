package com.example.tugether_be.chat.bean;

import com.example.tugether_be.chat.bean.small.CreateChatRoomDAOBean;
import com.example.tugether_be.chat.bean.small.CreateChatRoomUserDAOBean;
import com.example.tugether_be.chat.bean.small.SaveChatRoomDAOBean;
import com.example.tugether_be.chat.bean.small.SaveChatRoomUserDAOBean;
import com.example.tugether_be.chat.domain.ChatRoomDAO;
import com.example.tugether_be.chat.domain.ChatRoomUserDAO;
import com.example.tugether_be.chat.domain.DTO.RequestChatRoomSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class CreateChatRoomBean {

    CreateChatRoomDAOBean createChatRoomDAOBean;
    SaveChatRoomDAOBean saveChatRoomDAOBean;
    CreateChatRoomUserDAOBean createChatRoomUserDAOBean;
    SaveChatRoomUserDAOBean saveChatRoomUserDAOBean;

    @Autowired
    public CreateChatRoomBean(CreateChatRoomDAOBean createChatRoomDAOBean, SaveChatRoomDAOBean saveChatRoomDAOBean, CreateChatRoomUserDAOBean createChatRoomUserDAOBean, SaveChatRoomUserDAOBean saveChatRoomUserDAOBean) {
        this.createChatRoomDAOBean = createChatRoomDAOBean;
        this.saveChatRoomDAOBean = saveChatRoomDAOBean;
        this.createChatRoomUserDAOBean = createChatRoomUserDAOBean;
        this.saveChatRoomUserDAOBean = saveChatRoomUserDAOBean;
    }

    // 채팅방 생성
    public Long exec(RequestChatRoomSaveDTO requestChatRoomSaveDTO){

        // 채팅방과 유저 자동 입장을 위한 현재 시간
        LocalDateTime now = LocalDateTime.now();

        // 채팅방 DAO 생성
        ChatRoomDAO chatRoomDAO = createChatRoomDAOBean.exec(requestChatRoomSaveDTO, now);
        if (chatRoomDAO == null) return null;

        // 채팅방 DAO 저장
        saveChatRoomDAOBean.exec(chatRoomDAO);

        Long chatRoomId = chatRoomDAO.getChatRoomId();

        // 채팅방 생성 요청한 유저 입장 DAO 생성
        ChatRoomUserDAO chatRoomUserDAO = createChatRoomUserDAOBean.exec(chatRoomId, requestChatRoomSaveDTO, now);
        if (chatRoomUserDAO == null) return null;

        // 채팅방 생성 요청한 유저 입장 DAO 저장
        saveChatRoomUserDAOBean.exec(chatRoomUserDAO);

        // 채팅방 UUID(PK) 값 반환
        return chatRoomId;
    }
}
