package com.cemalturkcan.ecommerce.domain.store.cart.cart.web;

import com.cemalturkcan.ecommerce.domain.store.cart.cartproduct.web.CartProductResponse;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
@Builder
public class CartResponse implements Serializable {
    private final Long id;
    private final Double price;

    private List<CartProductResponse> cartProducts;
}
