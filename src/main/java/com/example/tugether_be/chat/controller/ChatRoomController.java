package com.example.tugether_be.chat.controller;

import com.example.tugether_be.chat.domain.DTO.RequestChatRoomSaveDTO;
import com.example.tugether_be.chat.domain.DTO.RequestChatRoomsGetDTO;
import com.example.tugether_be.chat.domain.DTO.ResponseChatRoomsGetDTO;
import com.example.tugether_be.chat.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/chat/room")
public class ChatRoomController {

    ChatRoomService chatRoomService;

    @Autowired
    public ChatRoomController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    // 채팅방 생성
    @PostMapping
    public ResponseEntity<Map<String, Object>> createChatRoom(@RequestBody RequestChatRoomSaveDTO requestChatRoomSaveDTO){

        // 채팅방 생성된 UUID 값 가져오기
        Long chatRoomId = chatRoomService.createChatRoom(requestChatRoomSaveDTO);

        // 해당 UUID 존재 유무 확인
        boolean success = (chatRoomId == null) ? false : true;

        // Map 형태로 묶어 저장
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "채팅방 생성 성공" : "채팅방 생성 실패");
        requestMap.put("chatRoomId", chatRoomId);

        // 반환
        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }


    // 채팅방 목록 조회
    @GetMapping("/all/{userId}")
    public ResponseEntity<Map<String, Object>> getAllChatRoom(@PathVariable("userId") Long userId){

        // 채팅방 목록 DTO
        List<ResponseChatRoomsGetDTO> requestChatRoomSaveDTOS = chatRoomService.getChatRooms(userId);

        // 해당 채팅방 목록 조회 성공 여부
        boolean success = (requestChatRoomSaveDTOS == null) ? false : true;

        // Map 형태로 묶어 저장
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", success);
        requestMap.put("message", success ? "채팅방 목록 조회 성공" : "채팅방 목록 조회 실패");
        requestMap.put("requestChatRoomSaveDTOS", requestChatRoomSaveDTOS);

        // 반환
        return ResponseEntity.status(HttpStatus.OK).body(requestMap);
    }
}