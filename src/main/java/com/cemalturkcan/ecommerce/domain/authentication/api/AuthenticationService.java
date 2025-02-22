package com.cemalturkcan.ecommerce.domain.authentication.api;

import com.cemalturkcan.ecommerce.domain.authentication.web.AuthResponse;
import com.cemalturkcan.ecommerce.domain.authentication.web.LoginRequest;
import com.cemalturkcan.ecommerce.domain.authentication.web.RegisterRequest;

public interface AuthenticationService {
    AuthResponse login(LoginRequest request);


    AuthResponse register(RegisterRequest request);

}
