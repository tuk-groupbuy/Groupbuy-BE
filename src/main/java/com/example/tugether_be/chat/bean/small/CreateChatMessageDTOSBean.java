package com.example.tugether_be.chat.bean.small;

import com.example.tugether_be.chat.domain.ChatMessageDAO;
import com.example.tugether_be.chat.domain.DTO.ResponseChatMessageGetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateChatMessageDTOSBean {

    CreateChatMessageDTOBean createChatMessageDTOBean;

    @Autowired
    public CreateChatMessageDTOSBean(CreateChatMessageDTOBean createChatMessageDTOBean) {
        this.createChatMessageDTOBean = createChatMessageDTOBean;
    }

    public List<ResponseChatMessageGetDTO> exec(Page<ChatMessageDAO> chatMessageDAOS) {
        // DTO list 객체 생성
        List<ResponseChatMessageGetDTO> responseChatMessageGetDTOS = new ArrayList<>();

        // for문을 통해 DAO를 하나씩 꺼내 DTO에 삽입
        for (ChatMessageDAO chatMessageDAO : chatMessageDAOS.getContent()) {

            // DTO 생성
            ResponseChatMessageGetDTO responseChatMessageGetDTO = createChatMessageDTOBean.exec(chatMessageDAO);

            // DTO 리스트에 각 DTO 추가
            responseChatMessageGetDTOS.add(responseChatMessageGetDTO);
        }

        // DTO 리스트 반환
        return responseChatMessageGetDTOS;
    }
}
