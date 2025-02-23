package com.cemalturkcan.ecommerce.domain.store.order.order.api;

import com.cemalturkcan.ecommerce.domain.store.order.order.web.OrderResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderResponse createOrder();

    Page<OrderResponse> getOrders(Pageable pageable);
}
