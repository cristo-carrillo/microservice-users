package com.pragma.powerup.infrastructure.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthDto {
    private Long id;
    private String name;
    private String lastName;
    private Long identityDocument;
    private String phone;
    private String email;
    private String password;
    private String role;
}
