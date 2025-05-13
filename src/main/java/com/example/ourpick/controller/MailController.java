package com.example.ourpick.controller;

import com.example.ourpick.service.MailService;
import com.example.ourpick.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/mail")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;
    private final RedisService redisService;

    // 이메일 인증 코드 전송
    @PostMapping("/send-email")
    public ResponseEntity<Map<String, String>> sendAuthEmail(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String authCode = mailService.generateAuthCode();
        redisService.saveAuthCode(email, authCode);
        mailService.sendAuthEmail(email, authCode);

        Map<String, String> response = new HashMap<>();
        response.put("message", "EMAIL_SENT");
        return ResponseEntity.ok(response);
    }

    // 인증 코드 확인
    @PostMapping("/verify-code")
    public ResponseEntity<Map<String, String>> verifyAuthCode(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String code = request.get("code");
        String authCode = redisService.getAuthCode(email);

        Map<String, String> response = new HashMap<>();
        if (authCode != null && authCode.equals(code)) {
            redisService.deleteAuthCode(email);
            response.put("message", "SUCCESS");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "FAIL");
            return ResponseEntity.status(400).body(response);
        }
    }
}
