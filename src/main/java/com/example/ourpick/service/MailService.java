package com.example.ourpick.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;

    // 인증 코드 생성 (랜덤 6자리 숫자)
    public String generateAuthCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }

    // 이메일 전송
    public void sendAuthEmail(String toEmail, String authCode) {
        String subject = "회원가입 이메일 인증 코드";
        String content = "안녕하세요, ECARS 서비스입니다.<br><br>"
                + "인증 코드: <strong>" + authCode + "</strong><br><br>"
                + "해당 코드를 입력하여 인증을 완료해주세요.";

        sendEmail(toEmail, subject, content);
    }

    // 이메일 전송 로직
    public void sendEmail(String toEmail, String subject, String content) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(content, true);
            helper.setFrom("your@Email");

            mailSender.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException("인증 코드 전송 실패", e);
        }
    }
}
