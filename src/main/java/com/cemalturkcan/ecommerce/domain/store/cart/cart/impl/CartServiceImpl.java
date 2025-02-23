package com.cemalturkcan.ecommerce.domain.store.cart.cart.impl;

import com.cemalturkcan.ecommerce.domain.store.cart.cart.api.CartService;
import com.cemalturkcan.ecommerce.domain.store.cart.cart.web.AddOrRemoveProductCartRequest;
import com.cemalturkcan.ecommerce.domain.store.cart.cart.web.CartResponse;
import com.cemalturkcan.ecommerce.domain.store.cart.cartproduct.api.CartProductService;
import com.cemalturkcan.ecommerce.domain.store.cart.cartproduct.web.CartProductResponse;
import com.cemalturkcan.ecommerce.domain.user.customer.api.CustomerService;
import com.cemalturkcan.ecommerce.domain.user.customer.impl.CustomerCreatedEvent;
import com.cemalturkcan.ecommerce.library.ObjectConverter;
import com.cemalturkcan.ecommerce.library.enums.MessageCodes;
import com.cemalturkcan.ecommerce.library.exception.CoreException;
import com.cemalturkcan.ecommerce.library.security.SecurityContext;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CustomerService customerService;
    private final CartProductService cartProductService;


    @Override
    public CartResponse getCart() {
        var customer = customerService.getCustomerByUserId(SecurityContext.getUserId());
        return toResponse(cartRepository.findCartByCustomerId(customer.getId(), CartStatus.ACTIVE.name()));
    }

    @Override
    @Transactional
    public CartResponse addProductToCart(AddOrRemoveProductCartRequest request) {
        return addOrRemoveProductToCart(request, request.getQuantity());
    }

    @Override
    @Transactional
    public CartResponse removeProductFromCart(AddOrRemoveProductCartRequest request) {
        return addOrRemoveProductToCart(request, -request.getQuantity());
    }

    @Override
    @Transactional
    public CartResponse emptyCart() {
        var customer = customerService.getCustomerByUserId(SecurityContext.getUserId());
        cartProductService.emptyCart(customer.getId());
        cartRepository.updateCartPrice(customer.getId(), 0.0);
        return toResponse(cartRepository.findCartByCustomerId(customer.getId(), CartStatus.ACTIVE.name()));
    }

    @Override
    @Transactional
    public CartResponse InactivateCart() {
        var customer = customerService.getCustomerByUserId(SecurityContext.getUserId());
        if (!cartRepository.anyProductInCart(customer.getId())) {
            throw new CoreException(MessageCodes.CART_IS_EMPTY, "Cart is empty");
        }

        cartRepository.updateCartStatus(customer.getId(), CartStatus.INACTIVE.name());
        createCart(customer.getId());
        return toResponse(cartRepository.findCartByCustomerId(customer.getId(), CartStatus.INACTIVE.name()));
    }

    private CartResponse addOrRemoveProductToCart(AddOrRemoveProductCartRequest request, int quantity) {
        var customer = customerService.getCustomerByUserId(SecurityContext.getUserId());
        var customerCartId = cartRepository.findCartByCustomerIdGetId(customer.getId(), CartStatus.ACTIVE.name());
        var price = cartProductService.addOrRemoveProductToCart(customerCartId, request.getProductId(), quantity);
        cartRepository.updateCartPriceBySum(customer.getId(), price);
        return toResponse(cartRepository.findCartByCustomerId(customer.getId(), CartStatus.ACTIVE.name()));
    }

    @EventListener
    @Transactional
    public void handleCustomerCreatedEvent(CustomerCreatedEvent event) {
        createCart(event.getCustomerId());
    }

    private void createCart(Long customerId) {
        var cart = new Cart();
        cart.setCustomerId(customerId);
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