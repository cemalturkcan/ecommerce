package com.cemalturkcan.ecommerce.domain.store.cart.cart.web;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddOrRemoveProductCartRequest {
    private Long productId;
    private Integer quantity;
}
