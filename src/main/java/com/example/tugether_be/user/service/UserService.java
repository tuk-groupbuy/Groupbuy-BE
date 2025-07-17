package com.example.tugether_be.user.service;

import com.example.tugether_be.auth.entity.User;
import com.example.tugether_be.auth.repository.UserRepository;
import com.example.tugether_be.auth.util.SecurityUtil;
import com.example.tugether_be.user.dto.NicknameDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void updateNickname(NicknameDto nicknameDto) {
        User user = userRepository.findById(nicknameDto.getUserId()).get();
        user.updateNickname(nicknameDto.getNickname());
        userRepository.save(user);
    }

}
