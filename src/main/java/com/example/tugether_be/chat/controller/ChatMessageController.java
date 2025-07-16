package com.example.tugether_be.chat.controller;

import com.example.tugether_be.chat.domain.DTO.ResponseChatMessageGetDTO;
import com.example.tugether_be.chat.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/chat/message")
public class ChatMessageController {

    ChatMessageService chatMessageService;

    @Autowired
    public ChatMessageController(ChatMessageService chatMessageService) {
        this.chatMessageService = chatMessageService;
    }

    // 뒤로 가기 후 다시 들어갈 때 기존에 있던 메세지 10(default)개씩 제공 api
    @GetMapping("/{chatRoomId}")
    public ResponseEntity<Map<String, Object>> getChatMessages(@PathVariable Long chatRoomId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){

        // 채팅 내역 가져오기
        List<ResponseChatMessageGetDTO> responseChatMessageGetDTOS = chatMessageService.getChatMessages(chatRoomId, page, size);

        // 해당 DTO 존재 유무 확인
        boolean success = (responseChatMessageGetDTOS == null) ? false : true;

        // Map 형태로 묶어 저장
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "대화 10개 조회 성공" : "대화 10개 조회 실패");
        requestMap.put("commentId", responseChatMessageGetDTOS);

        // 반환
        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }
}
