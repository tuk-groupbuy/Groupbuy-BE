package com.example.tugether_be.notification.controller;

import com.example.tugether_be.notification.domain.DTO.RequestJoinApprovalDTO;
import com.example.tugether_be.notification.domain.DTO.ResponseNotificationDTO;
import com.example.tugether_be.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
@CrossOrigin("*")
public class NotificationController {

    private final NotificationService notificationService;

    // 알림 목록 조회
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ResponseNotificationDTO>> getNotifications(@PathVariable Long userId) {
        return ResponseEntity.ok(notificationService.getNotificationList(userId));
    }

    // 참여 요청 승인
    @PostMapping("/approve")
    public ResponseEntity<String> approveRequest(@RequestBody RequestJoinApprovalDTO dto) {
        notificationService.approveJoinRequest(dto);
        return ResponseEntity.ok("참여 요청이 승인되었습니다.");
    }

    // 참여 요청 반려
    @PostMapping("/reject")
    public ResponseEntity<String> rejectRequest(@RequestBody RequestJoinApprovalDTO dto) {
        notificationService.rejectJoinRequest(dto);
        return ResponseEntity.ok("참여 요청이 반려되었습니다.");
    }

}
