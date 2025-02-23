package com.cemalturkcan.ecommerce.domain.store.order.order.web;

import com.cemalturkcan.ecommerce.domain.store.order.orderproduct.web.OrderProductResponse;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
@Builder
public class OrderResponse implements Serializable {
    private final Long id;
    private final String code;
    private final Double price;
    private List<OrderProductResponse> orderProducts;
}
