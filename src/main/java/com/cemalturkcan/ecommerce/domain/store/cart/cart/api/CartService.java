package com.cemalturkcan.ecommerce.domain.store.cart.cart.api;

import com.cemalturkcan.ecommerce.domain.store.cart.cart.web.AddOrRemoveProductCartRequest;
import com.cemalturkcan.ecommerce.domain.store.cart.cart.web.CartResponse;

public interface CartService {
    CartResponse getCart();

    CartResponse addProductToCart(AddOrRemoveProductCartRequest request);

    CartResponse removeProductFromCart(AddOrRemoveProductCartRequest request);

    CartResponse emptyCart();

    CartResponse InactivateCart();
}