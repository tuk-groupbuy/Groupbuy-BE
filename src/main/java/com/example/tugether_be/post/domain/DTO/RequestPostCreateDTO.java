package com.example.tugether_be.post.domain.DTO;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class RequestPostCreateDTO {
    private Long writerId;
    private String title;
    private String content;
    private int goalQuantity;
    private int price;
    private LocalDateTime deadline;
}
