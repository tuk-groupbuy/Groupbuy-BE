package com.example.tugether_be.post.domain.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Builder
public class RequestPostUpdateDTO {
    private String title;
    private String content;
    private int goalQuantity;
    private Integer minParticipants;
    private int price;
    private String imageUrl;
    private LocalDateTime deadline;
}
