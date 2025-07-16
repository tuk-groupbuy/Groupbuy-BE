package com.example.tugether_be.chat.repository;

import com.example.tugether_be.chat.domain.ChatMessageDAO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepositoryJPA extends JpaRepository<ChatMessageDAO, Long> {
    List<ChatMessageDAO> findByChatRoomId(Long chatRoomId);

    Page<ChatMessageDAO> findByChatRoomIdOrderBySendAtDesc(Long roomId, PageRequest pageRequest);

    ChatMessageDAO findTop1ByChatRoomIdOrderBySendAtDesc(Long chatRoomId);
}