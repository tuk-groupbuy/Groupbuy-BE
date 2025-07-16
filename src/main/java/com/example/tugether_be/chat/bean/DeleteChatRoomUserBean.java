package com.example.tugether_be.chat.bean;

import com.example.tugether_be.chat.bean.small.DeleteChatRoomUserDAOBean;
import com.example.tugether_be.chat.bean.small.GetChatRoomUserDAOBean;
import com.example.tugether_be.chat.domain.ChatRoomUserDAO;
import com.example.tugether_be.chat.domain.DTO.RequestChatRoomUserDeleteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DeleteChatRoomUserBean {

    GetChatRoomUserDAOBean getChatRoomUserDAOBean;
    DeleteChatRoomUserDAOBean deleteChatRoomUserDAOBean;

    @Autowired
    public DeleteChatRoomUserBean(GetChatRoomUserDAOBean getChatRoomUserDAOBean, DeleteChatRoomUserDAOBean deleteChatRoomUserDAOBean) {
        this.getChatRoomUserDAOBean = getChatRoomUserDAOBean;
        this.deleteChatRoomUserDAOBean = deleteChatRoomUserDAOBean;
    }

    public Long exec(RequestChatRoomUserDeleteDTO requestChatRoomUserDeleteDTO){
        ChatRoomUserDAO chatRoomUserDAO = getChatRoomUserDAOBean.exec(requestChatRoomUserDeleteDTO.getChatRoomId(), requestChatRoomUserDeleteDTO.getUserId());
        if (chatRoomUserDAO == null) return null;

        deleteChatRoomUserDAOBean.exec(chatRoomUserDAO);

        return chatRoomUserDAO.getChatRoomUserId();
    }
}
