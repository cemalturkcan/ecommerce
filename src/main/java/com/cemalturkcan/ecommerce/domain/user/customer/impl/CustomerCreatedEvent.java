package com.cemalturkcan.ecommerce.domain.user.customer.impl;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CustomerCreatedEvent {
    private final Long customerId;
}
