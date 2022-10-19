package com.marindulja.ASM.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderGenerator {
    @Autowired
    private PasswordEncoder passwordEncoder;
    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
