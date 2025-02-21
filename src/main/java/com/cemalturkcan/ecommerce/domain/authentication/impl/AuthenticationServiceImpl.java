package com.cemalturkcan.ecommerce.domain.authentication.impl;

import com.cemalturkcan.ecommerce.domain.authentication.api.AuthenticationService;
import com.cemalturkcan.ecommerce.domain.authentication.web.AuthResponse;
import com.cemalturkcan.ecommerce.domain.authentication.web.LoginRequest;
import com.cemalturkcan.ecommerce.domain.authentication.web.RegisterRequest;
import com.cemalturkcan.ecommerce.domain.user.customer.api.CustomerService;
import com.cemalturkcan.ecommerce.domain.user.user.impl.UserServiceImpl;
import com.cemalturkcan.ecommerce.domain.user.user.web.UserResponse;
import com.cemalturkcan.ecommerce.library.enums.MessageCodes;
import com.cemalturkcan.ecommerce.library.exception.CoreException;
import com.cemalturkcan.ecommerce.library.security.jwt.api.JwtService;
import com.cemalturkcan.ecommerce.library.security.jwt.api.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


@Slf4j
@Service
@RequiredArgsConstructor
@Validated
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserServiceImpl userService;
    private final CustomerService customerService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public AuthResponse login(LoginRequest request) {
        UserResponse user;
        try {
            user = userService.getUserByEmail(request.getEmail());
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getId(),
                            request.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            throw new CoreException(MessageCodes.WRONG_CREDENTIALS, "Invalid email or password.");
        }

        return toResponse(jwtService.generateToken(
                toUserDto(user)
        ));
    }

    @Override
    public AuthResponse register(RegisterRequest request) {
        UserResponse user = userService.createUser(request);
        customerService.createCustomer(request, user.getId());

        return toResponse(jwtService.generateToken(
                toUserDto(user)
        ));
    }

    private static UserDto toUserDto(UserResponse user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    private AuthResponse toResponse(String token) {
        return AuthResponse.builder()
                .token(token)
                .build();
    }


}
