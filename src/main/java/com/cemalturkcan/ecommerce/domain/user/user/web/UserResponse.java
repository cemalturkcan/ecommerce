package com.cemalturkcan.ecommerce.domain.user.user.web;

import com.cemalturkcan.ecommerce.library.security.jwt.api.UserRole;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String email;
    private UserRole role;
}
