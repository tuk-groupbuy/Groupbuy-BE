package com.example.tugether_be.post.domain.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class RequestPostUpdateDTO {
    private String title;
    private String content;
    private int goalQuantity;
    private int price;
    private String imageUrl;
}
