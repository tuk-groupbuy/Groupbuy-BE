package com.example.tugether_be.chat.bean;

import com.example.tugether_be.chat.bean.small.CreateChatRoomUserDAOBean;
import com.example.tugether_be.chat.bean.small.SaveChatRoomUserDAOBean;
import com.example.tugether_be.chat.domain.ChatRoomUserDAO;
import com.example.tugether_be.chat.domain.DTO.RequestChatRoomUserSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SaveChatRoomUserBean {

    CreateChatRoomUserDAOBean createChatRoomUserDAOBean;
    SaveChatRoomUserDAOBean saveChatRoomUserDAOBean;

    @Autowired
    public SaveChatRoomUserBean(SaveChatRoomUserDAOBean saveChatRoomUserDAOBean, CreateChatRoomUserDAOBean createChatRoomUserDAOBean) {
        this.saveChatRoomUserDAOBean = saveChatRoomUserDAOBean;
        this.createChatRoomUserDAOBean = createChatRoomUserDAOBean;
    }

    public Long exec(RequestChatRoomUserSaveDTO requestChatRoomUserSaveDTO){
        ChatRoomUserDAO chatRoomUserDAO = createChatRoomUserDAOBean.exec(requestChatRoomUserSaveDTO);
        if (chatRoomUserDAO == null) return null;

        saveChatRoomUserDAOBean.exec(chatRoomUserDAO);

        return chatRoomUserDAO.getChatRoomId();
    }
}
