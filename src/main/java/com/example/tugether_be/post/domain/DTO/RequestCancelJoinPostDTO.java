package com.example.tugether_be.post.domain.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class RequestCancelJoinPostDTO {
    private Long postId;  // 취소할 게시글 ID
    private Long userId;  // 취소할 사용자 ID
}
