package com.cemalturkcan.ecommerce.domain.user.customer.web;

import com.cemalturkcan.ecommerce.domain.user.customer.api.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService service;




}
