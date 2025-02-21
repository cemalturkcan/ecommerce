package com.cemalturkcan.ecommerce.domain.authentication.web;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginRequest {
    private static final String PASSWORD_REGEXP = "^\\S{8,}$";
    private static final String PASSWORD_NOT_BLANK_MESSAGE = "validation.password.notBlank";
    private static final String PASSWORD_REGEXP_MESSAGE = "validation.password.regexp";
    private static final String EMAIL_NOT_BLANK_MESSAGE = "validation.email.notBlank";
    private static final String EMAIL_EMAIL_MESSAGE = "validation.email.email";
    @NotBlank(message = EMAIL_NOT_BLANK_MESSAGE)
    @Email(message = EMAIL_EMAIL_MESSAGE)
    private String email;
    @NotBlank(message = PASSWORD_NOT_BLANK_MESSAGE)
    @Pattern(regexp = PASSWORD_REGEXP, message = PASSWORD_REGEXP_MESSAGE)
    private String password;
}
