package com.example.ourpick.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "accounts")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    @Id
    @Column(length = 254, nullable = false, unique = true)
    private String email;

    @Column(length = 20, nullable = false, unique = true)
    private String name;

    @Setter
    @Column(length = 200, nullable = false)
    private String password;

    @Column(nullable = false)
    @Builder.Default
    private boolean isAdmin = false;

}
