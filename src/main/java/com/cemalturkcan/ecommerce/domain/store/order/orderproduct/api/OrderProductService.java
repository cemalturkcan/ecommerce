package com.cemalturkcan.ecommerce.domain.store.order.orderproduct.api;

import com.cemalturkcan.ecommerce.domain.store.cart.cartproduct.web.CartProductResponse;

import java.util.List;

public interface OrderProductService {
    void createOrderProducts(List<CartProductResponse> cartProducts);
}
