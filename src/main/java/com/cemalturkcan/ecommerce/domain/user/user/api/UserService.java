package com.cemalturkcan.ecommerce.domain.user.user.api;

import com.cemalturkcan.ecommerce.domain.user.user.web.CreateAdminRequest;
import com.cemalturkcan.ecommerce.domain.user.user.web.UserRequest;
import com.cemalturkcan.ecommerce.domain.user.user.web.UserResponse;

public interface UserService {
    UserResponse createUser(UserRequest request);
    UserResponse createAdmin(CreateAdminRequest request);
}
