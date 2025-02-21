package com.cemalturkcan.ecommerce.domain.authentication.web;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class AuthResponse {
    private String token;
}
