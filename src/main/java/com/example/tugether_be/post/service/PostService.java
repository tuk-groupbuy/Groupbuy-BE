package com.example.tugether_be.post.service;

import com.example.tugether_be.post.bean.*;
import com.example.tugether_be.post.domain.DTO.*;
import com.example.tugether_be.post.service.file.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final GetPostBean getPostBean;
    private final SearchPostBean searchPostBean;
    private final CreatePostBean createPostBean;
    private final UpdatePostBean updatePostBean;
    private final DeletePostBean deletePostBean;
    private final FileStorageService fileStorageService;

    // 게시글 전체 조회
    public List<ResponsePostSummaryDTO> getAllPosts() {
        return getPostBean.exec();
    }

    // 게시글 상세 조회 (requesterId optional)
    public ResponsePostDetailDTO getPostDetail(Long postId, Long requesterId) {
        if (requesterId == null) {
            return getPostBean.execDetail(postId);
        } else {
            return getPostBean.execDetail(postId, requesterId);
        }
    }

    // 게시글 작성
    public Long createPost(RequestPostCreateDTO dto, MultipartFile file) {
        String savedImagePath = fileStorageService.storeFile(file);
        return createPostBean.exec(dto, savedImagePath);
    }

    // 게시글 수정
    public void updatePost(Long postId, RequestPostUpdateDTO dto) {
        updatePostBean.exec(postId, dto);
    }

    // 게시글 삭제
    public void deletePost(Long postId) {
        deletePostBean.exec(postId);
    }
  
    // 게시글 검색
    public List<ResponsePostSummaryDTO> searchPosts(String keyword) {
        return searchPostBean.exec(keyword);
    }
}
