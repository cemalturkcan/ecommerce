package com.cemalturkcan.ecommerce.domain.store.order.order.web;

import com.cemalturkcan.ecommerce.domain.store.order.order.api.OrderService;
import com.cemalturkcan.ecommerce.library.rest.Response;
import com.cemalturkcan.ecommerce.library.rest.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public Response<Page<OrderResponse>> getProducts(Pageable pageable) {
        return ResponseBuilder.build(orderService.getOrders(pageable));
    }


    @PostMapping
    public Response<OrderResponse> createOrder() {
        return ResponseBuilder.build(orderService.createOrder());
    }
}
