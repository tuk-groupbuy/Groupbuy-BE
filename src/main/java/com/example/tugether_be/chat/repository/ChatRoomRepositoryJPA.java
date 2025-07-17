package com.example.tugether_be.chat.repository;

import com.example.tugether_be.chat.domain.ChatRoomDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChatRoomRepositoryJPA extends JpaRepository<ChatRoomDAO, Long> {

    ChatRoomDAO findByOwnerId(Long ownerId);
}
