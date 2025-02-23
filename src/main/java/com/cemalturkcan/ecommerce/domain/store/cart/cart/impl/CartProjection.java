package com.cemalturkcan.ecommerce.domain.store.cart.cart.impl;


public interface CartProjection {
    Long getId();

    Double getPrice();

    String getCartProducts();
}
