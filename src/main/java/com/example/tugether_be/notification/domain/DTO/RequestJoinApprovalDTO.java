package com.example.tugether_be.notification.domain.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestJoinApprovalDTO {

    private Long postId;     // 게시글 ID
    private Long userId;     // 참여 요청한 사용자 ID
    private Long writerId;   // 게시글 작성자 ID
}