package com.example.tugether_be.user.controller;

import com.example.tugether_be.auth.entity.User;
import com.example.tugether_be.auth.repository.UserRepository;
import com.example.tugether_be.auth.service.AuthService;
import com.example.tugether_be.auth.util.SecurityUtil;
import com.example.tugether_be.user.dto.NicknameDto;
import com.example.tugether_be.user.dto.ProfileResponse;
import com.example.tugether_be.user.dto.UserIdRequest;
import com.example.tugether_be.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.profiles.Profile;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    @PatchMapping("/nickname")
    public ResponseEntity<String> updateNickname(@RequestBody NicknameDto nicknameDto) {
        userService.updateNickname(nicknameDto);
        return ResponseEntity.ok("닉네임 수정 완료");
    }

    @GetMapping("/profile")
    public ResponseEntity<ProfileResponse> getProfile(@RequestParam Long userId) {
        ProfileResponse response = userService.getProfile(userId);
        return ResponseEntity.ok(response);
    }
}
