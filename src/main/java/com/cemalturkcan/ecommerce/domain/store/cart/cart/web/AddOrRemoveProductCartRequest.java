package com.cemalturkcan.ecommerce.domain.store.cart.cart.web;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddOrRemoveProductCartRequest {

    private static final String PRODUCT_ID_NOT_NULL_MESSAGE = "validation.productId.notNull";
    private static final String QUANTITY_NOT_NULL_MESSAGE = "validation.quantity.notNull";

    @NotNull(message = PRODUCT_ID_NOT_NULL_MESSAGE)
    private Long productId;
    @NotNull(message = QUANTITY_NOT_NULL_MESSAGE)
    private Integer quantity;
}
