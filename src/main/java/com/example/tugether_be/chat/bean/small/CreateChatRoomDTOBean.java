package com.example.tugether_be.chat.bean.small;

import com.example.tugether_be.chat.domain.ChatMessageDAO;
import com.example.tugether_be.chat.domain.ChatRoomDAO;
import com.example.tugether_be.chat.domain.DTO.ResponseChatMessageGetDTO;
import com.example.tugether_be.chat.domain.DTO.ResponseChatRoomsGetDTO;
import com.example.tugether_be.post.domain.PostDAO;
import org.springframework.stereotype.Component;

@Component
public class CreateChatRoomDTOBean {

    public ResponseChatRoomsGetDTO exec(PostDAO postDAO, ChatRoomDAO chatRoomDAO, ChatMessageDAO chatMessageDAO){
        return ResponseChatRoomsGetDTO.builder()
                .title(postDAO.getTitle())
                .chatRoomId(chatRoomDAO.getChatRoomId())
                .lastChatMessage(chatMessageDAO.getContent())
                .imageUrl(postDAO.getImageUrl())
                .sendAt(chatMessageDAO.getSendAt())
                .build();
    }

    public ResponseChatRoomsGetDTO exec(PostDAO postDAO, ChatRoomDAO chatRoomDAO){
        return ResponseChatRoomsGetDTO.builder()
                .title(postDAO.getTitle())
                .chatRoomId(chatRoomDAO.getChatRoomId())
                .lastChatMessage("아직 메세지가 없습니다")
                .imageUrl(postDAO.getImageUrl())
                .sendAt(null)
                .build();
    }
}
