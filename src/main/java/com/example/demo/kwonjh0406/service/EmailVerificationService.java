package com.example.demo.kwonjh0406.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.*;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailVerificationService {

    private final SesClient sesClient;

    private static final String FROM_EMAIL = "kwonjh0406@gmail.com"; // SES 인증된 이메일 주소
    private static final String SUBJECT = "이메일 인증 코드";

    public void sendVerificationCode(String toEmail) {
        String code = generateCode();

        String body = String.format("인증 코드는 [%s] 입니다. 10분 내에 입력해주세요.", code);

        SendEmailRequest request = SendEmailRequest.builder()
                .source(FROM_EMAIL)
                .destination(Destination.builder()
                        .toAddresses(toEmail)
                        .build())
                .message(Message.builder()
                        .subject(Content.builder().data(SUBJECT).charset("UTF-8").build())
                        .body(Body.builder()
                                .text(Content.builder().data(body).charset("UTF-8").build())
                                .build())
                        .build())
                .build();

        sesClient.sendEmail(request);
    }

    private String generateCode() {
        return UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }
}
