package com.main.txnbot.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JWTService {
    String extractEmail(String token);

    boolean validateToken(String token, UserDetails userDetails);

    String generateToken(String email);
}
