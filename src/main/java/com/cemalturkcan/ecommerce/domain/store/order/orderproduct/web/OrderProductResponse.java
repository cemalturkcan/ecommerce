package com.cemalturkcan.ecommerce.domain.store.order.orderproduct.web;

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
public class OrderProductResponse implements Serializable {
    private Long id;
    private Integer quantity;
    private Double price;
    private ProductResponse product;
}
