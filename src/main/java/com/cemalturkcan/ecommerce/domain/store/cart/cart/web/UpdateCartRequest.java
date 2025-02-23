package com.cemalturkcan.ecommerce.domain.store.cart.cart.web;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UpdateCartRequest {
    List<AddOrRemoveProductCartRequest> products;
}
