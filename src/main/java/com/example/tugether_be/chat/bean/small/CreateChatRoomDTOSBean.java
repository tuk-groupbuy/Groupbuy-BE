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
    CreateChatRoomDTOBean createChatRoomDTOBean;

    public CreateChatRoomDTOSBean(GetChatRoomDAOBean getChatRoomDAOBean, GetPostDAOBean getPostDAOBean, GetChatMessageDAOBean getChatMessageDAOBean, CreateChatRoomDTOBean createChatRoomDTOBean) {
        this.getChatRoomDAOBean = getChatRoomDAOBean;
        this.getPostDAOBean = getPostDAOBean;
        this.getChatMessageDAOBean = getChatMessageDAOBean;
        this.createChatRoomDTOBean = createChatRoomDTOBean;
    }

    public List<ResponseChatRoomsGetDTO> exec(List<ChatRoomUserDAO> chatRoomUserDAOS){

        List<ResponseChatRoomsGetDTO> responseChatRoomsGetDTOS = new ArrayList<>();

        for (ChatRoomUserDAO chatRoomUserDAO : chatRoomUserDAOS) {
            // 채팅방 정보 조회
            ChatRoomDAO chatRoomDAO = getChatRoomDAOBean.exec(chatRoomUserDAO.getChatRoomId());
            if (chatRoomDAO == null) {
                log.warn("ChatRoom not found for chatRoomId={}", chatRoomUserDAO.getChatRoomId());
                continue; // ❗null 무시하고 다음으로
            }

            // 게시글 정보 조회
            PostDAO postDAO = getPostDAOBean.exec(chatRoomDAO.getPostId());
            if (postDAO == null) {
                log.warn("Post not found for postId={}", chatRoomDAO.getPostId());
                continue;
            }

            // 최근 메시지 조회
            ChatMessageDAO chatMessageDAO = getChatMessageDAOBean.exec(chatRoomDAO.getChatRoomId());
            if (chatMessageDAO == null) {
                // 메시지가 없는 경우에도 기본값으로 응답 생성
                ResponseChatRoomsGetDTO responseChatRoomsGetDTO = createChatRoomDTOBean.exec(postDAO, chatRoomDAO);
                responseChatRoomsGetDTOS.add(responseChatRoomsGetDTO);
                continue;
            }

            // DTO 생성
            ResponseChatRoomsGetDTO responseChatRoomsGetDTO = createChatRoomDTOBean.exec(postDAO, chatRoomDAO, chatMessageDAO);

            // 리스트에 추가
            responseChatRoomsGetDTOS.add(responseChatRoomsGetDTO);
        }

        // 최근 메시지 기준 내림차순 정렬
        responseChatRoomsGetDTOS.sort(Comparator.comparing(ResponseChatRoomsGetDTO::getSendAt, Comparator.nullsLast(Comparator.reverseOrder())));

        return responseChatRoomsGetDTOS;
    }
}
