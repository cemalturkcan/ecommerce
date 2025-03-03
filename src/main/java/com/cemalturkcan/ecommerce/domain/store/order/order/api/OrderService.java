package com.cemalturkcan.ecommerce.domain.store.order.order.api;

import com.cemalturkcan.ecommerce.domain.store.order.order.web.OrderResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderResponse placeOrder();

    Page<OrderResponse> getOrders(Pageable pageable);

    OrderResponse getOrderByCode(String code);
}
