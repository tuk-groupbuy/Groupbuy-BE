package com.example.tugether_be.chat.controller;

import com.example.tugether_be.chat.domain.DTO.RequestChatRoomUserDeleteDTO;
import com.example.tugether_be.chat.domain.DTO.RequestChatRoomUserSaveDTO;
import com.example.tugether_be.chat.domain.DTO.ResponseChatRoomUserGetDTO;
import com.example.tugether_be.chat.service.ChatRoomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/chat/room/user")
public class ChatRoomUserController {

    ChatRoomUserService chatRoomUserService;

    @Autowired
    public ChatRoomUserController(ChatRoomUserService chatRoomUserService) {
        this.chatRoomUserService = chatRoomUserService;
    }

    // 채팅방 참여 인원 조회 api
    @GetMapping("/all/{chatRoomId}")
    public ResponseEntity<Map<String, Object>> getChatRoomUsers(@PathVariable Long chatRoomId){

        // 채팅방 참여 유저 DTO
        List<ResponseChatRoomUserGetDTO> responseChatRoomUserGetDTOS = chatRoomUserService.getChatRoomUsers(chatRoomId);

        // 해당 UUID 존재 유무 확인
        boolean success = (responseChatRoomUserGetDTOS == null) ? false : true;

        // Map 형태로 묶어 저장
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? " 채팅방 참여 인원 조회 성공" : "채팅방 참여 인원 조회 실패");
        requestMap.put("commentId", responseChatRoomUserGetDTOS);

        // 반환
        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }


    // 채팅방 참여
    @PostMapping("/join")
    public ResponseEntity<Map<String, Object>> joinChatRoom(@RequestBody RequestChatRoomUserSaveDTO requestCommentSaveDTO){

        Long chatRoomId = chatRoomUserService.joinChatRoom(requestCommentSaveDTO);

        boolean success = (chatRoomId == null) ? false : true;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "채팅방 참여 성공" : "채팅방 참여 실패");
        requestMap.put("commentId", chatRoomId);

        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }

    // 채팅방 나가기
    @DeleteMapping
    public ResponseEntity<Map<String, Object>> leaveChatRoom(@RequestBody RequestChatRoomUserDeleteDTO requestChatRoomUserDeleteDTO){

        // 나간 유저의 UUID 값 가져오기
        Long chatRoomUserId = chatRoomUserService.leaveChatRoom(requestChatRoomUserDeleteDTO);

        // 해당 UUID 존재 유무 확인
        boolean success = (chatRoomUserId == null) ? false : true;

        // Map 형태로 묶어 저장
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "대화 나가기 성공" : "대화 나가기 실패");
        requestMap.put("commentId", chatRoomUserId);

        // 반환
        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }
}
