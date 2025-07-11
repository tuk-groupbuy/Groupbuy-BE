package com.example.tugether_be.auth.controller;

import com.example.tugether_be.auth.dto.EmailDto;
import com.example.tugether_be.auth.service.EmailVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final EmailVerificationService emailVerificationService;

    @PostMapping("/email/issue-code")
    public ResponseEntity<String> issueEmailCode(@RequestBody EmailDto emailDto) {
        emailVerificationService.sendVerificationCode(emailDto.getEmail());
        return ResponseEntity.ok("인증 코드가 전송되었습니다.");
    }
}
