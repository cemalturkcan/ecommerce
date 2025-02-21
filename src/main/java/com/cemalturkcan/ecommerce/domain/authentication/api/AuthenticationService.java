package com.cemalturkcan.ecommerce.domain.authentication.api;

import com.cemalturkcan.ecommerce.domain.authentication.web.*;

public interface AuthenticationService {
    AuthResponse login(LoginRequest request);


    AuthResponse register(RegisterRequest request);

}
