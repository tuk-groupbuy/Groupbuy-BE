package com.example.tugether_be.chat.bean;

import com.example.tugether_be.chat.bean.small.CreateChatMessageDTOSBean;
import com.example.tugether_be.chat.bean.small.CreateChatPageRequestBean;
import com.example.tugether_be.chat.bean.small.GetChatMessageDAOSBean;
import com.example.tugether_be.chat.domain.ChatMessageDAO;
import com.example.tugether_be.chat.domain.DTO.ResponseChatMessageGetDTO;
import com.example.tugether_be.chat.domain.DTO.ResponseChatMessagePageGetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetChatMessagesBean {

    CreateChatPageRequestBean createChatPageRequestBean;
    GetChatMessageDAOSBean getChatMessageDAOSBean;
    CreateChatMessageDTOSBean createChatMessageDTOSBean;

    @Autowired
    public GetChatMessagesBean(CreateChatPageRequestBean createChatPageRequestBean, GetChatMessageDAOSBean getChatMessageDAOSBean, CreateChatMessageDTOSBean createChatMessageDTOSBean) {
        this.createChatPageRequestBean = createChatPageRequestBean;
        this.getChatMessageDAOSBean = getChatMessageDAOSBean;
        this.createChatMessageDTOSBean = createChatMessageDTOSBean;
    }

    // 채팅 내역 가져오기
    public ResponseChatMessagePageGetDTO exec(Long roomId, int page, int size){
        // pageRequest 객체 생성
        PageRequest pageRequest = createChatPageRequestBean.exec(page, size);

        // 해당 채팅방 채팅메시지 보낸 시간에 내림차순 페이지 형태로 가져오기
        Page<ChatMessageDAO> chatMessageDAOS = getChatMessageDAOSBean.exec(roomId, pageRequest);

        // DTO 생성 후 반환
        return createChatMessageDTOSBean.exec(chatMessageDAOS);
    }
}
