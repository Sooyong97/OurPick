package com.example.ourpick.dto;

import com.example.ourpick.domain.Account;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDto {
    private String id;
    private String name;
    private String email;
    private String password;
    private boolean isAdmin;

    public AccountDto(Account account) {
        this.id = account.getId();
        this.name = account.getName();
        this.email = account.getEmail();
        this.password = account.getPassword();
        this.isAdmin = account.isAdmin();
    }
}
