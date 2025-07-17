package com.example.tugether_be.post.controller;

import com.example.tugether_be.post.domain.DTO.RequestPostCreateDTO;
import com.example.tugether_be.post.domain.DTO.RequestPostUpdateDTO;
import com.example.tugether_be.post.domain.DTO.ResponsePostDetailDTO;
import com.example.tugether_be.post.domain.DTO.ResponsePostSummaryDTO;
import com.example.tugether_be.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PostController {

    private final PostService postService;

    // 게시글 전체 조회
    @GetMapping("/all")
    public ResponseEntity<List<ResponsePostSummaryDTO>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    // 게시글 상세 조회
    @GetMapping("/{postId}")
    public ResponseEntity<ResponsePostDetailDTO> getPostDetail(
            @PathVariable Long postId,
            @RequestParam(required = false) Long requesterId
    ) {
        return ResponseEntity.ok(postService.getPostDetail(postId, requesterId));
    }

    // 게시글 작성
    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<String> createPost(
            @RequestPart("dto") RequestPostCreateDTO dto,
            @RequestPart("file") MultipartFile file
    ) {
        Long postId = postService.createPost(dto, file);
        return ResponseEntity.ok("게시글 작성에 성공했습니다. (ID: " + postId + ")");
    }


    // 게시글 수정
    @PutMapping("/{postId}")
    public ResponseEntity<String> updatePost(@PathVariable Long postId, @RequestBody RequestPostUpdateDTO dto) {
        postService.updatePost(postId, dto);
        return ResponseEntity.ok("수정이 완료되었습니다.");
    }

    // 게시글 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok("삭제가 완료되었습니다.");
    }

    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchPosts(@RequestParam String keyword) {
        List<ResponsePostSummaryDTO> posts = postService.searchPosts(keyword);
        return ResponseEntity.ok(Map.of("posts", posts));
    }
}
