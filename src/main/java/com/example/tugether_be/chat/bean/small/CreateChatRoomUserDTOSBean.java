package com.example.tugether_be.chat.bean.small;

import com.example.tugether_be.chat.domain.ChatRoomDAO;
import com.example.tugether_be.chat.domain.ChatRoomUserDAO;
import com.example.tugether_be.chat.domain.DTO.ResponseChatRoomUserGetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateChatRoomUserDTOSBean {

    CreateChatRoomUserDTOBean createChatRoomUserDTOBean;

    @Autowired
    public CreateChatRoomUserDTOSBean(CreateChatRoomUserDTOBean createChatRoomUserDTOBean) {
        this.createChatRoomUserDTOBean = createChatRoomUserDTOBean;
    }

    public List<ResponseChatRoomUserGetDTO> exec(List<ChatRoomUserDAO> chatRoomUserDAOS, ChatRoomDAO chatRoomDAO) {
        List<ResponseChatRoomUserGetDTO> responseChatRoomUserGetDTOS = new ArrayList<>();

        for (ChatRoomUserDAO chatRoomUserDAO : chatRoomUserDAOS) {

            ResponseChatRoomUserGetDTO responseChatMessageGetDTO = createChatRoomUserDTOBean.exec(chatRoomUserDAO, chatRoomDAO);

            responseChatRoomUserGetDTOS.add(responseChatMessageGetDTO);
        }

        return responseChatRoomUserGetDTOS;
    }
}
