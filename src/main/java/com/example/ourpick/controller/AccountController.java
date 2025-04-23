package com.example.ourpick.controller;

import com.example.ourpick.dto.AccountDto;
import com.example.ourpick.service.AccountService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "*")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody AccountDto accountDto) {
        accountService.register(accountDto);
        return ResponseEntity.ok("회원가입 성공");
    }
}
