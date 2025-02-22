package com.cemalturkcan.ecommerce.domain.store.cart.cartproduct.impl;

import com.cemalturkcan.ecommerce.domain.store.cart.cartproduct.api.CartProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartProductServiceImpl implements CartProductService {
    private final CartProductRepository cartProductRepository;

    @Override
    public CartProduct createCartProduct(CartProduct cartProduct) {
        return cartProductRepository.save(cartProduct);
    }

}
