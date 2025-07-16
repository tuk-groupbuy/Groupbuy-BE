package com.example.tugether_be.chat.bean;

import com.example.tugether_be.chat.bean.small.CreateChatRoomUsersDTOBean;
import com.example.tugether_be.chat.bean.small.GetChatRoomDAOBean;
import com.example.tugether_be.chat.bean.small.GetChatRoomUserDAOBean;
import com.example.tugether_be.chat.domain.ChatRoomDAO;
import com.example.tugether_be.chat.domain.ChatRoomUserDAO;
import com.example.tugether_be.chat.domain.DTO.ResponseChatRoomUserGetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class GetChatRoomUsersBean {

    GetChatRoomUserDAOBean getChatRoomUserDAOBean;
    CreateChatRoomUsersDTOBean createChatRoomUsersDTOBean;
    GetChatRoomDAOBean getChatRoomDAOBean;

    @Autowired
    public GetChatRoomUsersBean(GetChatRoomUserDAOBean getChatRoomUserDAOBean, CreateChatRoomUsersDTOBean createChatRoomUsersDTOBean, GetChatRoomDAOBean getChatRoomDAOBean) {
        this.getChatRoomUserDAOBean = getChatRoomUserDAOBean;
        this.createChatRoomUsersDTOBean = createChatRoomUsersDTOBean;
        this.getChatRoomDAOBean = getChatRoomDAOBean;
    }

    public List<ResponseChatRoomUserGetDTO> exec(Long chatRoomId){
        ChatRoomDAO chatRoomDAO = getChatRoomDAOBean.exec(chatRoomId);
        if (chatRoomDAO == null) return null;

        List<ChatRoomUserDAO> chatRoomUserDAOS = getChatRoomUserDAOBean.exec(chatRoomId);
        if (chatRoomUserDAOS.isEmpty()) return null;

        return createChatRoomUsersDTOBean.exec(chatRoomUserDAOS, chatRoomDAO);
    }
}
