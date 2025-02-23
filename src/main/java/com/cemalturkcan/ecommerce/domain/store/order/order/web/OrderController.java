package com.cemalturkcan.ecommerce.domain.store.order.order.web;

import com.cemalturkcan.ecommerce.domain.store.order.order.api.OrderService;
import com.cemalturkcan.ecommerce.library.rest.Response;
import com.cemalturkcan.ecommerce.library.rest.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER')")
    public Response<Page<OrderResponse>> getProducts(Pageable pageable) {
        return ResponseBuilder.build(orderService.getOrders(pageable));
    }

    @GetMapping("/{code}")
    @PreAuthorize("hasAnyAuthority('USER')")
    public Response<OrderResponse> getOrder(@PathVariable String code) {
        return ResponseBuilder.build(orderService.getOrderByCode(code));
    }


    @PostMapping
    @PreAuthorize("hasAnyAuthority('USER')")
    public Response<OrderResponse> placeOrder() {
        return ResponseBuilder.build(orderService.placeOrder());
    }
}
