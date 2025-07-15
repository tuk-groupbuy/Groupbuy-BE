package com.example.tugether_be.notification.repository;

import com.example.tugether_be.notification.domain.NotificationDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepositoryJPA extends JpaRepository<NotificationDAO, Long> {

    // 특정 사용자에게 온 알림만 조회 (최신순)
    List<NotificationDAO> findByReceiverIdOrderByCreatedAtDesc(Long receiverId);
}
