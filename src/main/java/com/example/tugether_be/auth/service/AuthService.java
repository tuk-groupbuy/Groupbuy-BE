package com.example.tugether_be.auth.service;

import com.example.tugether_be.auth.dto.SignupRequest;
import com.example.tugether_be.auth.entity.User;
import com.example.tugether_be.auth.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final EmailVerificationService emailVerificationService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @PostConstruct
    public void createTestUser() {
        if (userRepository.findByEmail("rnjswnsgud08406@tukorea.ac.kr").isEmpty()) {
            userRepository.save(User.builder()
                    .email("rnjswnsgud08406@tukorea.ac.kr")
                    .password(passwordEncoder.encode("asdf")) // ✅ 암호화 중요
                    .nickname("테스트유저")
                    .build());
        }
    }


    public void signup(SignupRequest request) {
        emailVerificationService.verifyCodeOrThrow(request.getEmail(), request.getCode());

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = User.builder()
                .email(request.getEmail())
                .password(encodedPassword)
                .build();

        userRepository.save(user);

        log.info("회원가입 완료: {}", user.getEmail());
    }
}

