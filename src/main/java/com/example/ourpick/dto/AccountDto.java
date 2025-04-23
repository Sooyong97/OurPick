package com.example.ourpick.dto;

import com.example.ourpick.domain.Account;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDto {
    private String email;
    private String name;
    private String password;
    private boolean isAdmin;

    public AccountDto(Account account) {
        this.email = account.getEmail();
        this.name = account.getName();
        this.password = account.getPassword();
        this.isAdmin = account.isAdmin();
    }
}
