package com.cemalturkcan.ecommerce.domain.user.customer.impl;

import com.cemalturkcan.ecommerce.domain.authentication.web.RegisterRequest;
import com.cemalturkcan.ecommerce.domain.user.customer.api.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public void createCustomer(RegisterRequest request, Long userId) {
        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setSurname(request.getSurname());
        customer.setUserId(userId);
        customerRepository.save(customer);
    }
}
