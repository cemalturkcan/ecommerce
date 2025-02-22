package com.cemalturkcan.ecommerce.domain.store.cart.cartproduct.web;

import com.cemalturkcan.ecommerce.domain.store.product.web.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartProductResponse implements Serializable {
    private Long id;
    private Integer quantity;
    private ProductResponse product;
}
