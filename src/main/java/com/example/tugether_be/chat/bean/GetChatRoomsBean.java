package com.example.tugether_be.chat.bean;

import com.example.tugether_be.chat.bean.small.*;
import com.example.tugether_be.chat.domain.ChatMessageDAO;
import com.example.tugether_be.chat.domain.ChatRoomDAO;
import com.example.tugether_be.chat.domain.ChatRoomUserDAO;
import com.example.tugether_be.chat.domain.DTO.RequestChatRoomsGetDTO;
import com.example.tugether_be.chat.domain.DTO.ResponseChatRoomsGetDTO;
import com.example.tugether_be.post.bean.small.GetPostDAOBean;
import com.example.tugether_be.post.domain.PostDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
@Slf4j
public class GetChatRoomsBean {

    GetChatRoomUserDAOBean getChatRoomUserDAOBean;
    CreateChatRoomDTOSBean createChatRoomDTOSBean;

    @Autowired
    public GetChatRoomsBean(GetChatRoomUserDAOBean getChatRoomUserDAOBean, CreateChatRoomDTOSBean createChatRoomDTOSBean) {
        this.getChatRoomUserDAOBean = getChatRoomUserDAOBean;
        this.createChatRoomDTOSBean = createChatRoomDTOSBean;
    }

    public List<ResponseChatRoomsGetDTO> exec(RequestChatRoomsGetDTO requestChatRoomsGetDTO) {

        List<ChatRoomUserDAO> chatRoomUserDAOS = getChatRoomUserDAOBean.exec(requestChatRoomsGetDTO.getUserId());
        log.info("info log chatRoomUserDAOS={}", chatRoomUserDAOS);

        return createChatRoomDTOSBean.exec(chatRoomUserDAOS);
    }
}
