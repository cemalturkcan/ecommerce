package com.cemalturkcan.ecommerce.domain.store.cart.cartproduct.web;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CartProductRequest {
    private static final String PRODUCT_ID_NOT_NULL_MESSAGE = "validation.product.id.notNull";
    private static final String QUANTITY_NOT_NULL_MESSAGE = "validation.quantity.notNull";

    @NotNull(message = PRODUCT_ID_NOT_NULL_MESSAGE)
    private Long productId;
    @NotNull(message = QUANTITY_NOT_NULL_MESSAGE)
    private Integer quantity;
}
