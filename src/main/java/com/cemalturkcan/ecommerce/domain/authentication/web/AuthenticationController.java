package com.cemalturkcan.ecommerce.domain.authentication.web;

import com.cemalturkcan.ecommerce.domain.authentication.api.AuthenticationService;
import com.cemalturkcan.ecommerce.library.rest.Response;
import com.cemalturkcan.ecommerce.library.rest.ResponseBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/login")
    public Response<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse authResponse = service.login(request);
        return ResponseBuilder.build(
                AuthResponse.builder()
                        .token(authResponse.getToken())
                        .build()
        );
    }

    @PostMapping("/register")
    public Response<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseBuilder.build(service.register(request));
    }
}
