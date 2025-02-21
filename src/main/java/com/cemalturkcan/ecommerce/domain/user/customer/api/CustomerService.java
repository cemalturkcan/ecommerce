package com.cemalturkcan.ecommerce.domain.user.customer.api;

import com.cemalturkcan.ecommerce.domain.authentication.web.RegisterRequest;

public interface CustomerService {
    void createCustomer(RegisterRequest request, Long userId);
}
