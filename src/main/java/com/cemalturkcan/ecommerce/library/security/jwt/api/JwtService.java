package com.cemalturkcan.ecommerce.library.security.jwt.api;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.Map;

public interface JwtService {
    String extractUserName(String token);
    String generateToken(UserDto user);
    boolean isTokenValid(String token, UserDetails userDetails);
    boolean isTokenExpired(String token);
    UserRole getUserPermission(String token);
    String generateTokenWithCustomExpiration(UserDto user, Date customExpiration);
}