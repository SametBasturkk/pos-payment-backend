package com.pospayment.pospayment.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Hasher {

    private final BCryptPasswordEncoder passwordEncoder;

    public Hasher() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public String hashPassword(String password) {
        log.info("Hashing password");
        return passwordEncoder.encode(password);
    }

    public boolean comparePasswords(String rawPassword, String encodedPassword) {
        log.info("Comparing passwords");
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
