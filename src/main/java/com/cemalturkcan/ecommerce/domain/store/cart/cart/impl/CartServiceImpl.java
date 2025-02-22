package com.cemalturkcan.ecommerce.domain.store.cart.cart.impl;

import com.cemalturkcan.ecommerce.domain.store.cart.cart.api.CartService;
import com.cemalturkcan.ecommerce.domain.store.cart.cart.impl.projection.CartProjection;
import com.cemalturkcan.ecommerce.domain.store.cart.cart.web.CartResponse;
import com.cemalturkcan.ecommerce.domain.store.cart.cartproduct.web.CartProductResponse;
import com.cemalturkcan.ecommerce.domain.user.customer.api.CustomerService;
import com.cemalturkcan.ecommerce.domain.user.customer.impl.CustomerCreatedEvent;
import com.cemalturkcan.ecommerce.library.ObjectConverter;
import com.cemalturkcan.ecommerce.library.security.SecurityContext;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CustomerService customerService;

    @Override
    public CartResponse getCart() {
        var customer = customerService.getCustomerByUserId(SecurityContext.getUserId());
        return toResponse(cartRepository.findCartByCustomerId(customer.getId()));
    }


    @EventListener
    public void handleCustomerCreatedEvent(CustomerCreatedEvent event) {
        var cart = new Cart();
        cart.setCustomerId(event.getCustomerId());
        cart.setPrice(0.0);
        cart.setStatus(CartStatus.ACTIVE);
        cartRepository.save(cart);
    }

    private CartResponse toResponse(CartProjection cartProjection) {
        return CartResponse.builder()
                .id(cartProjection.getId())
                .price(cartProjection.getPrice())
                .cartProducts(
                        ObjectConverter.readValue(
                                cartProjection.getCartProducts(), new TypeReference<List<CartProductResponse>>() {
                                }
                        ).orElse(List.of())
                )
                .build();
    }

}