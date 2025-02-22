package com.cemalturkcan.ecommerce.library.security.jwt.api;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

public interface JwtService {
    String extractUserName(String token);

    String generateToken(UserDto user);

    boolean isTokenValid(String token, UserDetails userDetails);

    boolean isTokenExpired(String token);

}