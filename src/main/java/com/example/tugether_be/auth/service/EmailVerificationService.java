package com.example.tugether_be.auth.service;


import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.*;

import java.time.Duration;
import java.util.UUID;

@Slf4j
@Service
public class EmailVerificationService {

    private final SesClient sesClient;
    private final Cache<String, String> emailCodeCache;

    private static final String FROM_EMAIL = "kwonjh0406@gmail.com";
    private static final String SUBJECT = "이메일 인증 코드";

    public EmailVerificationService(SesClient sesClient) {
        this.sesClient = sesClient;
        this.emailCodeCache = Caffeine.newBuilder()
                .expireAfterWrite(Duration.ofMinutes(10))
                .maximumSize(10000)
                .build();
    }

    public void sendVerificationCode(String email) {
        validateEmailDomain(email);
        String code = generateCode();

        // 캐시에 저장 (같은 키일 경우 덮어쓰기)
        emailCodeCache.put(email, code);

        String body = String.format("[TUgether] 인증 코드는 [%s] 입니다. 10분 내에 입력해주세요.", code);

        SendEmailRequest request = SendEmailRequest.builder()
                .source(FROM_EMAIL)
                .destination(Destination.builder()
                        .toAddresses(email)
                        .build())
                .message(Message.builder()
                        .subject(Content.builder().data(SUBJECT).charset("UTF-8").build())
                        .body(Body.builder()
                                .text(Content.builder().data(body).charset("UTF-8").build())
                                .build())
                        .build())
                .build();

        sesClient.sendEmail(request);
        log.info("이메일 [{}] 에 인증 코드 [{}] 전송 완료", email, code);
    }

    public boolean verifyCode(String email, String inputCode) {
        String cachedCode = emailCodeCache.getIfPresent(email);
        if (cachedCode == null) {
            throw new IllegalStateException("인증 코드가 만료되었거나 존재하지 않습니다.");
        }
        if (!cachedCode.equalsIgnoreCase(inputCode)) {
            throw new IllegalArgumentException("인증 코드가 일치하지 않습니다.");
        }

        // 인증 성공 시 캐시에서 제거
        emailCodeCache.invalidate(email);
        return true;
    }

    private String generateCode() {
        return UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }

    private void validateEmailDomain(String email) {
        if (!email.toLowerCase().endsWith("@tukorea.ac.kr")) {
            throw new IllegalArgumentException("tukorea.ac.kr 도메인만 허용됩니다.");
        }
    }

    public void verifyCodeOrThrow(String email, String inputCode) {
        String cachedCode = emailCodeCache.getIfPresent(email);
        if (cachedCode == null) {
            throw new IllegalStateException("인증 코드가 만료되었거나 존재하지 않습니다.");
        }
        if (!cachedCode.equalsIgnoreCase(inputCode)) {
            throw new IllegalArgumentException("인증 코드가 일치하지 않습니다.");
        }

        // 성공 시 캐시에서 제거
        emailCodeCache.invalidate(email);
    }

}

