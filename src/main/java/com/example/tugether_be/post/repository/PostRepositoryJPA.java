package com.example.tugether_be.post.repository;

import com.example.tugether_be.post.domain.PostDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepositoryJPA extends JpaRepository<PostDAO, Long> {

    // 삭제되지 않은 전체 게시글 조회
    List<PostDAO> findAllByIsDeletedFalse();

    // 작성자 ID로 게시글 조회 (사용X)
    List<PostDAO> findByWriterIdAndIsDeletedFalse(Long writerId);

    // 특정 ID의 게시글 상세 조회
    Optional<PostDAO> findByPostIdAndIsDeletedFalse(Long postId);

    // 모집 완료 여부로 게시글 조회 (사용X)
    List<PostDAO> findByIsCompleted(Boolean isCompleted);

    // 키워드 기반 게시물 검색
    @Query("SELECT p FROM PostDAO p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<PostDAO> searchByTitleLike(@Param("keyword") String keyword);
}
