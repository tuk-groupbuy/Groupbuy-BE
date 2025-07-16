package com.example.tugether_be.post.domain.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestPostCreateDTO {
    private Long writerId;
    private String title;
    private String content;
    private int goalQuantity;
    private int pricePerOne;
}
