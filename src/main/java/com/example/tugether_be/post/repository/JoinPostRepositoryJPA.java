package com.example.tugether_be.post.repository;

import com.example.tugether_be.post.domain.JoinPostDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JoinPostRepositoryJPA extends JpaRepository<JoinPostDAO, Long> {

    Optional<JoinPostDAO> findByPost_PostIdAndUserId(Long postId, Long userId);

    void deleteByPost_PostIdAndUserId(Long postId, Long userId);

}
