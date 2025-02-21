package com.cemalturkcan.ecommerce.domain.user.user.web;

import com.cemalturkcan.ecommerce.domain.user.user.api.UserService;
import com.cemalturkcan.ecommerce.library.rest.Response;
import com.cemalturkcan.ecommerce.library.rest.ResponseBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping
    public Response<UserResponse> createAdmin(
            @Valid @RequestBody CreateAdminRequest request
    ) {
        return ResponseBuilder.build(userService.createAdmin(request));
    }


}
