package com.example.tugether_be.chat.bean.small;

import com.example.tugether_be.chat.domain.ChatMessageDAO;
import com.example.tugether_be.chat.domain.ChatRoomUserDAO;
import com.example.tugether_be.chat.repository.ChatMessageRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetChatMessageDAOSBean {

    ChatMessageRepositoryJPA chatMessageRepositoryJPA;
    GetChatMessageDAOBean getChatMessageDAOBean;

    @Autowired
    public GetChatMessageDAOSBean(ChatMessageRepositoryJPA chatMessageRepositoryJPA, GetChatMessageDAOBean getChatMessageDAOBean) {
        this.chatMessageRepositoryJPA = chatMessageRepositoryJPA;
        this.getChatMessageDAOBean = getChatMessageDAOBean;
    }

    // 해당 채팅방에 보낸 시간 내림차순 페이지 형태로 메시지 가져오기
    public Page<ChatMessageDAO> exec(Long roomId, PageRequest pageRequest){
        return chatMessageRepositoryJPA.findByChatRoomIdOrderBySendAtDesc(roomId, pageRequest);
    }
}