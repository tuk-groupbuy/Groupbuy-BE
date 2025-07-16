package com.example.tugether_be.chat.bean.small;

import com.example.tugether_be.chat.domain.ChatMessageDAO;
import com.example.tugether_be.chat.domain.ChatRoomDAO;
import com.example.tugether_be.chat.domain.ChatRoomUserDAO;
import com.example.tugether_be.chat.domain.DTO.ResponseChatRoomsGetDTO;
import com.example.tugether_be.post.bean.small.GetPostDAOBean;
import com.example.tugether_be.post.domain.PostDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Component
public class CreateChatRoomDTOSBean {

    GetChatRoomDAOBean getChatRoomDAOBean;
    GetPostDAOBean getPostDAOBean;
    GetChatMessageDAOBean getChatMessageDAOBean;

    public CreateChatRoomDTOSBean(GetChatRoomDAOBean getChatRoomDAOBean, GetPostDAOBean getPostDAOBean, GetChatMessageDAOBean getChatMessageDAOBean) {
        this.getChatRoomDAOBean = getChatRoomDAOBean;
        this.getPostDAOBean = getPostDAOBean;
        this.getChatMessageDAOBean = getChatMessageDAOBean;
    }

    public List<ResponseChatRoomsGetDTO> exec(List<ChatRoomUserDAO> chatRoomUserDAOS){

        List<ResponseChatRoomsGetDTO> responseChatRoomsGetDTOS = new ArrayList<>();

        for (ChatRoomUserDAO chatRoomUserDAO : chatRoomUserDAOS) {
            // 채팅방 정보 조회
            ChatRoomDAO chatRoomDAO = getChatRoomDAOBean.exec(chatRoomUserDAO.getChatRoomId());
            if (chatRoomDAO == null) return null;
            log.info("info log chatRoomDAO={}", chatRoomDAO);;

            // 게시글 정보 조회
            PostDAO postDAO = getPostDAOBean.exec(chatRoomDAO.getPostId());

            // 최근 메시지 조회
            ChatMessageDAO chatMessageDAO = getChatMessageDAOBean.exec(chatRoomDAO.getChatRoomId());
            log.info("info log chatMessageDAO={}", chatMessageDAO);

            // DTO 생성
            ResponseChatRoomsGetDTO responseChatRoomsGetDTO = ResponseChatRoomsGetDTO.builder()
                    .title(postDAO.getTitle())
                    .chatRoomId(chatRoomDAO.getChatRoomId())
                    .lastChatMessage(chatMessageDAO.getContent())
                    .imageUrl(postDAO.getImageUrl())
                    .sendAt(chatMessageDAO.getSendAt())
                    .build();

            // 리스트에 추가
            responseChatRoomsGetDTOS.add(responseChatRoomsGetDTO);
        }

        // 최근 메시지 기준 내림차순 정렬
        responseChatRoomsGetDTOS.sort(Comparator.comparing(ResponseChatRoomsGetDTO::getSendAt, Comparator.nullsLast(Comparator.reverseOrder())));

        return responseChatRoomsGetDTOS;
    }
}
