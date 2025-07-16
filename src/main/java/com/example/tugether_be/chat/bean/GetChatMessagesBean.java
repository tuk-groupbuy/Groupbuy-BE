package com.example.tugether_be.chat.bean;

import com.example.tugether_be.chat.bean.small.CreateChatMessagesDTOBean;
import com.example.tugether_be.chat.bean.small.CreateChatPageRequestBean;
import com.example.tugether_be.chat.bean.small.GetChatMessagesDAOBean;
import com.example.tugether_be.chat.domain.ChatMessageDAO;
import com.example.tugether_be.chat.domain.DTO.ResponseChatMessageGetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class GetChatMessagesBean {

    CreateChatPageRequestBean createChatPageRequestBean;
    GetChatMessagesDAOBean getChatMessagesDAOBean;
    CreateChatMessagesDTOBean createChatMessagesDTOBean;

    @Autowired
    public GetChatMessagesBean(CreateChatPageRequestBean createChatPageRequestBean, GetChatMessagesDAOBean getChatMessagesDAOBean, CreateChatMessagesDTOBean createChatMessagesDTOBean) {
        this.createChatPageRequestBean = createChatPageRequestBean;
        this.getChatMessagesDAOBean = getChatMessagesDAOBean;
        this.createChatMessagesDTOBean = createChatMessagesDTOBean;
    }

    // 채팅 내역 가져오기
    public List<ResponseChatMessageGetDTO> exec(Long roomId, int page, int size){
        // pageRequest 객체 생성
        PageRequest pageRequest = createChatPageRequestBean.exec(page, size);

        // 해당 채팅방 채팅메시지 보낸 시간에 내림차순 페이지 형태로 가져오기
        Page<ChatMessageDAO> chatMessageDAOS = getChatMessagesDAOBean.exec(roomId, pageRequest);

        // DTO 생성 후 반환
        return createChatMessagesDTOBean.exec(chatMessageDAOS);
    }
}
