package com.cemalturkcan.ecommerce.domain.user.user.impl;

import com.cemalturkcan.ecommerce.domain.user.user.api.UserService;
import com.cemalturkcan.ecommerce.domain.user.user.web.CreateAdminRequest;
import com.cemalturkcan.ecommerce.domain.user.user.web.UserRequest;
import com.cemalturkcan.ecommerce.domain.user.user.web.UserResponse;
import com.cemalturkcan.ecommerce.library.enums.MessageCodes;
import com.cemalturkcan.ecommerce.library.exception.CoreException;
import com.cemalturkcan.ecommerce.library.security.jwt.api.UserRole;
import com.cemalturkcan.ecommerce.library.utils.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public UserResponse createUser(UserRequest request) {
        if (repository.getUserByEmail(request.getEmail()).isPresent()) {
            throw new CoreException(MessageCodes.ENTITY_ALREADY_EXISTS, Constants.USER_EXCEPTION, request.getEmail());
        }

        User user = userRequestToEntity(new User(), request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User savedUser = repository.save(user);
        return toResponse(savedUser);
    }

    @Override
    public UserResponse createAdmin(CreateAdminRequest request) {
        if (repository.getUserByEmail(request.getEmail()).isPresent()) {
            throw new CoreException(MessageCodes.ENTITY_ALREADY_EXISTS, Constants.USER_EXCEPTION, request.getEmail());
        }

        User user = createAdminRequestToEntity(new User(), request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User savedUser = repository.save(user);
        return toResponse(savedUser);
    }

    private UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    private User createAdminRequestToEntity(User user, CreateAdminRequest request) {
        toEntity(user, request, UserRole.ADMIN);
        return user;
    }

    private User userRequestToEntity(User user, UserRequest request) {
        toEntity(user, request, UserRole.USER);
        return user;
    }

    private static void toEntity(User user, UserRequest request, UserRole user1) {
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(user1);
        user.setStatus(Boolean.TRUE);
    }

    public UserResponse getUserByEmail(String email) {
        User user = repository.getUserByEmail(email)
                .orElseThrow(() -> new CoreException(MessageCodes.ENTITY_NOT_FOUND, Constants.USER_EXCEPTION, email));
        return toResponse(user);
    }

}
