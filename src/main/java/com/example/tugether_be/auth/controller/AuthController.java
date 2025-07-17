package com.example.tugether_be.auth.controller;

import com.example.tugether_be.auth.dto.EmailDto;
import com.example.tugether_be.auth.dto.LoginRequest;
import com.example.tugether_be.auth.dto.SignupRequest;
import com.example.tugether_be.auth.dto.UserIdResponse;
import com.example.tugether_be.auth.entity.User;
import com.example.tugether_be.auth.repository.UserRepository;
import com.example.tugether_be.auth.service.AuthService;
import com.example.tugether_be.auth.service.EmailVerificationService;
import jakarta.servlet.http.HttpServletRequest;
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
    private final AuthService authService;
    private final UserRepository userRepository;

    @PostMapping("/email/issue-code")
    public ResponseEntity<String> issueEmailCode(@RequestBody EmailDto emailDto) {
        emailVerificationService.sendVerificationCode(emailDto.getEmail());
        return ResponseEntity.ok("인증 코드가 전송되었습니다.");
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest request, HttpServletRequest httpRequest) {
        authService.signup(request, httpRequest);
        return ResponseEntity.ok("회원가입 성공");
    }

    @PostMapping("/login")
    public Long login(@RequestBody LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail()).get();
        return user.getId();
    }
}
