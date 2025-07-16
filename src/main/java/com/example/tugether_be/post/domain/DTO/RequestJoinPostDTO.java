package com.example.tugether_be.post.domain.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


 //  게시글 참여 요청 시 클라이언트로부터 전달받는 데이터
 @Data
 @Builder
public class RequestJoinPostDTO {
    private Long postId;   // 참여할 게시글 ID
    private Long userId;   // 참여하는 사용자 ID
}
