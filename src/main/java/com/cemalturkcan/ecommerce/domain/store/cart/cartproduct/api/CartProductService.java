package com.cemalturkcan.ecommerce.domain.store.cart.cartproduct.api;

public interface CartProductService {

    Double addOrRemoveProductToCart(Long cartId, Long productId, int quantity);

    void emptyCart(Long id);
}
