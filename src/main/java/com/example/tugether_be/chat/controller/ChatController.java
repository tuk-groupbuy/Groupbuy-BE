package com.example.tugether_be.chat.controller;

import com.example.tugether_be.chat.domain.DTO.ChatMessageDTO;
import com.example.tugether_be.chat.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class ChatController {

    ChatService chatService;
    SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public ChatController(ChatService chatService, SimpMessagingTemplate simpMessagingTemplate){
        this.chatService = chatService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    // 클라이언트 → /app/chat/send 로 전송
    @MessageMapping("/chat/send")
    public ChatMessageDTO sendMessage(ChatMessageDTO chatMessageDTO) {
        // 서버에서 시간 관리
        chatMessageDTO.setSendAt(LocalDateTime.now());

        // DB 저장
        chatService.saveMessage(chatMessageDTO);

        // roomId에 따른 경로로 메세지 전송
        String destination = "/topic/chat/" + chatMessageDTO.getChatRoomId();
        simpMessagingTemplate.convertAndSend(destination, chatMessageDTO);
        return chatMessageDTO;
    }

}
