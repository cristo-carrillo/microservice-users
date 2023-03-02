package com.pragma.powerup.application.utils.imp;

import com.pragma.powerup.application.utils.IPasswordEncryption;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
@RequiredArgsConstructor
public class PasswordEncryption implements IPasswordEncryption {
    private  final PasswordEncoder passwordEncoder;

    @Override
    public String passwordEncryption(String password) {
        return passwordEncoder.encode(password);
    }
}
