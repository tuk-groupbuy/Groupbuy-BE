package com.example.tugether_be.post.controller;

import com.example.tugether_be.post.domain.DTO.RequestJoinPostDTO;
import com.example.tugether_be.post.domain.DTO.RequestCancelJoinPostDTO;
import com.example.tugether_be.post.service.JoinPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post/join")
@RequiredArgsConstructor
@CrossOrigin("*")
public class JoinPostController {

    private final JoinPostService joinPostService;

    // 게시글 참여 요청
    @PostMapping
    public ResponseEntity<String> joinPost(@RequestBody RequestJoinPostDTO dto) {
        joinPostService.joinPost(dto);
        return ResponseEntity.ok("참여가 완료되었습니다.");
    }

    // 게시글 참여 취소
    @DeleteMapping
    public ResponseEntity<String> cancelJoinPost(@RequestBody RequestCancelJoinPostDTO dto) {
        joinPostService.cancelJoinPost(dto);
        return ResponseEntity.ok("참여가 취소되었습니다.");
    }
}
