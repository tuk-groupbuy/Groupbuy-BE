package com.example.demo.kwonjh0406.controller;

import com.example.demo.kwonjh0406.dto.SignupRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/auth")
public class AuthController {

    @PostMapping("/signup")
    public ResponseEntity<String> signup(SignupRequest signupRequest) {
        return
    }
}
