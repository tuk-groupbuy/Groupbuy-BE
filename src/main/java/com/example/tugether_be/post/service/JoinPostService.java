package com.example.tugether_be.post.service;

import com.example.tugether_be.post.bean.JoinPostBean;
import com.example.tugether_be.post.bean.CancelJoinPostBean;
import com.example.tugether_be.post.domain.DTO.RequestJoinPostDTO;
import com.example.tugether_be.post.domain.DTO.RequestCancelJoinPostDTO;
import jakarta.transaction.Transactional; // 또는 org.springframework.transaction.annotation.Transactional
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinPostService {

    private final JoinPostBean joinPostBean;
    private final CancelJoinPostBean cancelJoinPostBean;

    // 게시글 참여
    public void joinPost(RequestJoinPostDTO dto) {
        joinPostBean.exec(dto);
    }

    // 게시글 참여 취소
    @Transactional
    public void cancelJoinPost(RequestCancelJoinPostDTO dto) {
        cancelJoinPostBean.exec(dto.getPostId(), dto.getUserId());
    }
}
