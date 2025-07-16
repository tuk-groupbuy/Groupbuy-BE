package com.example.tugether_be.chat.repository;

import com.example.tugether_be.chat.domain.ChatRoomDAO;
import com.example.tugether_be.chat.domain.ChatRoomUserDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ChatRoomUserRepositoryJPA extends JpaRepository<ChatRoomUserDAO, Long> {
    boolean existsByUserIdAndChatRoomId(Long userId, Long chatRoomId);

    ChatRoomUserDAO findByChatRoomIdAndUserId(Long chatRoomId, Long userId);

    List<ChatRoomUserDAO> findAllByChatRoomId(Long chatRoomId);

    List<ChatRoomDAO> findChatRoomByUserId(Long userId);

    List<ChatRoomUserDAO> findByUserId(Long userId);
}
