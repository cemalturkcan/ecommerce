package com.cemalturkcan.ecommerce.domain.user.customer.impl;

import com.cemalturkcan.ecommerce.domain.authentication.web.RegisterRequest;
import com.cemalturkcan.ecommerce.domain.user.customer.api.CustomerDto;
import com.cemalturkcan.ecommerce.domain.user.customer.api.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ApplicationEventPublisher eventPublisher;


    @Override
    @Transactional
    public void createCustomer(RegisterRequest request, Long userId) {
        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setSurname(request.getSurname());
        customer.setUserId(userId);
        customerRepository.save(customer);
        FireCustomerCreatedEvent(customer);
    }

    @Override
    public CustomerDto getCustomerByUserId(Long userId) {
        return customerRepository.findByUserId(userId)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    private CustomerDto toDto(Customer customer) {
        return CustomerDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .surname(customer.getSurname())
                .build();
    }

    private void FireCustomerCreatedEvent(Customer customer) {
        eventPublisher.publishEvent(
                CustomerCreatedEvent.builder()
                        .customerId(customer.getId())
                        .build()
        );
    }
}
