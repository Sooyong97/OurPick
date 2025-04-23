package com.example.ourpick.controller;

import com.example.ourpick.dto.AccountDto;
import com.example.ourpick.dto.EmailDto;
import com.example.ourpick.service.AccountService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "*")
public class AccountController {

    private final AccountService accountService;
    
    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody AccountDto dto) {
        accountService.register(dto);
        return ResponseEntity.ok("회원가입 성공");
    }
    
    // 이메일 중복 확인
    @PostMapping("/emailcheck")
    public ResponseEntity<Map<String, Boolean>> checkEmail(@RequestBody EmailDto dto) {
        boolean isAvailable = accountService.isEmailAvailable(dto.getEmail());

        Map<String, Boolean> result = new HashMap<>();
        result.put("valid", isAvailable);
        return ResponseEntity.ok(result);
    }
}
