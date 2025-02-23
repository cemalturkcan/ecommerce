package com.cemalturkcan.ecommerce.domain.store.order.order.impl;


public interface OrderProjection {
    Long getId();

    String getCode();

    Double getPrice();

    String getOrderProducts();
}
