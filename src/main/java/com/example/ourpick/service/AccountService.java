package com.example.ourpick.service;

import com.example.ourpick.domain.Account;
import com.example.ourpick.dto.AccountDto;
import com.example.ourpick.repository.AccountRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 회원가입
    public void register(AccountDto dto) {
        if (accountRepository.existsByEmail(dto.getId())) {
            throw new IllegalArgumentException("이미 가입된 아이디입니다.");
        }

        Account account = Account.builder()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build();

        accountRepository.save(account);
    }
}
