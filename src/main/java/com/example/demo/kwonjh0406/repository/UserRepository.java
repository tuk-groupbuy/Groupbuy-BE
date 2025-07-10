package com.example.demo.kwonjh0406.repository;

import com.example.demo.kwonjh0406.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
