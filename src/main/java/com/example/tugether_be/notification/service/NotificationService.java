package com.example.tugether_be.notification.service;

import com.example.tugether_be.notification.bean.ApproveJoinRequestBean;
import com.example.tugether_be.notification.bean.RejectJoinRequestBean;
import com.example.tugether_be.notification.bean.GetNotificationListBean;
import com.example.tugether_be.notification.domain.DTO.RequestJoinApprovalDTO;
import com.example.tugether_be.notification.domain.DTO.ResponseNotificationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final GetNotificationListBean getNotificationListBean;
    private final ApproveJoinRequestBean approveJoinRequestBean;
    private final RejectJoinRequestBean rejectJoinRequestBean;

    // 알림 목록 조회
    public List<ResponseNotificationDTO> getNotificationList(Long userId) {
        return getNotificationListBean.exec(userId);
    }

    // 참여 요청 승인
    public void approveJoinRequest(RequestJoinApprovalDTO dto) {
        approveJoinRequestBean.exec(dto);
    }

    // 참여 요청 반려
    public void rejectJoinRequest(RequestJoinApprovalDTO dto) {
        rejectJoinRequestBean.exec(dto);
    }
}
