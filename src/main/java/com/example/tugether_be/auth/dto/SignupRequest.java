package com.example.tugether_be.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupRequest {

    private String email;

    private String password;

    private String code;

    private String nickname;
}

