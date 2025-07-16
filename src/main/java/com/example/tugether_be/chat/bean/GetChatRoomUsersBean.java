package com.example.tugether_be.chat.bean;

import com.example.tugether_be.chat.bean.small.CreateChatRoomUserDTOSBean;
import com.example.tugether_be.chat.bean.small.GetChatRoomDAOBean;
import com.example.tugether_be.chat.bean.small.GetChatRoomUserDAOBean;
import com.example.tugether_be.chat.domain.ChatRoomDAO;
import com.example.tugether_be.chat.domain.ChatRoomUserDAO;
import com.example.tugether_be.chat.domain.DTO.ResponseChatRoomUserGetDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class GetChatRoomUsersBean {

    GetChatRoomUserDAOBean getChatRoomUserDAOBean;
    CreateChatRoomUserDTOSBean createChatRoomUserDTOSBean;
    GetChatRoomDAOBean getChatRoomDAOBean;

    @Autowired
    public GetChatRoomUsersBean(GetChatRoomUserDAOBean getChatRoomUserDAOBean, CreateChatRoomUserDTOSBean createChatRoomUserDTOSBean, GetChatRoomDAOBean getChatRoomDAOBean) {
        this.getChatRoomUserDAOBean = getChatRoomUserDAOBean;
        this.createChatRoomUserDTOSBean = createChatRoomUserDTOSBean;
        this.getChatRoomDAOBean = getChatRoomDAOBean;
    }

    public List<ResponseChatRoomUserGetDTO> exec(Long chatRoomId){
        ChatRoomDAO chatRoomDAO = getChatRoomDAOBean.exec(chatRoomId);
        if (chatRoomDAO == null) return null;
        log.info("info log chatRoomDAO={}", chatRoomDAO);

        List<ChatRoomUserDAO> chatRoomUserDAOS = getChatRoomUserDAOBean.exec2(chatRoomId);
        if (chatRoomUserDAOS.isEmpty()) return null;
        log.info("info log chatRoomUserDAOS={}", chatRoomUserDAOS);

        return createChatRoomUserDTOSBean.exec(chatRoomUserDAOS, chatRoomDAO);
    }
}
