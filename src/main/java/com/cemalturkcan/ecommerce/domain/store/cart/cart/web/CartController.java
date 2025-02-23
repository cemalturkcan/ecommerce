package com.cemalturkcan.ecommerce.domain.store.cart.cart.web;

import com.cemalturkcan.ecommerce.domain.store.cart.cart.api.CartService;
import com.cemalturkcan.ecommerce.library.rest.Response;
import com.cemalturkcan.ecommerce.library.rest.ResponseBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER')")
    public Response<CartResponse> getCart() {
        return ResponseBuilder.build(cartService.getCart());
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('USER')")
    public Response<CartResponse> updateCart(
            @RequestBody
            UpdateCartRequest request) {
        return ResponseBuilder.build(cartService.updateCart(request));
    }


    @PutMapping("/empty-cart")
    @PreAuthorize("hasAnyAuthority('USER')")
    public Response<CartResponse> emptyCart() {
        return ResponseBuilder.build(cartService.emptyCart());
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('USER')")
    public Response<CartResponse> addProductToCart(
            @Valid
            @RequestBody
            AddOrRemoveProductCartRequest request) {
        return ResponseBuilder.build(cartService.addProductToCart(request));
    }

    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('USER')")
    public Response<CartResponse> removeProductFromCart(
            @Valid
            @RequestBody
            AddOrRemoveProductCartRequest request) {
        return ResponseBuilder.build(cartService.removeProductFromCart(request));
    }
}
