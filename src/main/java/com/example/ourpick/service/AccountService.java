package com.example.ourpick.service;

import com.example.ourpick.domain.Account;
import com.example.ourpick.dto.AccountDto;
import com.example.ourpick.repository.AccountRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 회원가입
    public void register(AccountDto dto) {
        if (accountRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("이미 가입된 아이디입니다.");
        }

        String nickname = generateNickname();
        Account account = Account.builder()
                .email(dto.getEmail())
                .name(nickname)
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build();

        accountRepository.save(account);
    }

    // 이메일 중복 확인
    public boolean isEmailAvailable(String email) {
        return !accountRepository.existsByEmail(email);
    }
    
    // 랜덤 닉네임 생성
    private String generateNickname() {
        return "Pick" + UUID.randomUUID().toString().substring(0, 6);
    }
}
