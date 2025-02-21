package com.cemalturkcan.ecommerce.domain.authentication.web;

import com.cemalturkcan.ecommerce.domain.user.user.web.UserRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
public class RegisterRequest extends UserRequest {
    private static final String NAME_NOT_BLANK_MESSAGE = "validation.name.notBlank";
    private static final String SURNAME_NOT_BLANK_MESSAGE = "validation.surname.notBlank";

    @NotBlank(message = NAME_NOT_BLANK_MESSAGE)
    private String name;
    @NotBlank(message = SURNAME_NOT_BLANK_MESSAGE)
    private String surname;
}
