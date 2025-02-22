package com.cemalturkcan.ecommerce.domain.store.cart.cartproduct.api;

import com.cemalturkcan.ecommerce.domain.store.cart.cartproduct.impl.CartProduct;

public interface CartProductService {
    CartProduct createCartProduct(CartProduct cartProduct);
}
