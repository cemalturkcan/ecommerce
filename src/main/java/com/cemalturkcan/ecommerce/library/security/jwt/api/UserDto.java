package com.cemalturkcan.ecommerce.library.security.jwt.api;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserDto implements Serializable {
    private Long id;
    private String email;
    private String password;
    private UserRole role;
    private Boolean status;
}