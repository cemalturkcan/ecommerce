package com.cemalturkcan.ecommerce.domain.store.cart.cart.web;

import com.cemalturkcan.ecommerce.domain.store.cart.cart.api.CartService;
import com.cemalturkcan.ecommerce.library.rest.Response;
import com.cemalturkcan.ecommerce.library.rest.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PreAuthorize("hasAnyAuthority('USER')")
    @GetMapping
    public Response<CartResponse> getCart() {
        return ResponseBuilder.build(cartService.getCart());
    }



    @PutMapping("/empty-cart")
    public Response<CartResponse> emptyCart() {
        return ResponseBuilder.build(cartService.emptyCart());
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @PostMapping
    public Response<CartResponse> addProductToCart(
            @RequestBody
            AddOrRemoveProductCartRequest request) {
        return ResponseBuilder.build(cartService.addProductToCart(request));
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @DeleteMapping
    public Response<CartResponse> removeProductFromCart(
            @RequestBody
            AddOrRemoveProductCartRequest request) {
        return ResponseBuilder.build(cartService.removeProductFromCart(request));
    }
}
