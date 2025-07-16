package com.example.tugether_be.chat.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ChatRoomDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long chatRoomId;
    Long ownerId;

    LocalDateTime createAt;
}
