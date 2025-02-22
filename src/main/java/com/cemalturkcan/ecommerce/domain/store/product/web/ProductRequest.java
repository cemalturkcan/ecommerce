package com.cemalturkcan.ecommerce.domain.store.product.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductRequest {
    private static final String NAME_NOT_BLANK_MESSAGE = "validation.name.notBlank";
    private static final String PRICE_NOT_NOT_NULL_MESSAGE = "validation.price.notNull";
    private static final String STOCK_NOT_NOT_NULL_MESSAGE = "validation.stock.notNull";

    @NotBlank(message = NAME_NOT_BLANK_MESSAGE)
    private String name;

    @NotNull(message = PRICE_NOT_NOT_NULL_MESSAGE)
    private Double price;

    @NotNull(message = STOCK_NOT_NOT_NULL_MESSAGE)
    private Integer stock;

}
