package com.cemalturkcan.ecommerce.domain.store.cart.cart.impl.projection;


public interface CartProjection {
    Long getId();

    Double getPrice();

    String getCartProducts();
}
