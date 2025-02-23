package com.cemalturkcan.ecommerce.domain.store.order.order.impl;

import com.cemalturkcan.ecommerce.domain.store.cart.cart.api.CartService;
import com.cemalturkcan.ecommerce.domain.store.order.order.api.OrderService;
import com.cemalturkcan.ecommerce.domain.store.order.order.web.OrderResponse;
import com.cemalturkcan.ecommerce.domain.store.order.orderproduct.api.OrderProductService;
import com.cemalturkcan.ecommerce.domain.store.order.orderproduct.web.OrderProductResponse;
import com.cemalturkcan.ecommerce.domain.user.customer.api.CustomerService;
import com.cemalturkcan.ecommerce.library.ObjectConverter;
import com.cemalturkcan.ecommerce.library.security.SecurityContext;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderProductService orderProductService;
    private final CartService cartService;
    private final CustomerService customerService;

    @Override
    @Transactional
    public OrderResponse placeOrder() {
        var cart = cartService.InactivateCart();
        Order order = new Order();
        order.setCartId(cart.getId());
        order.setCode(UUID.randomUUID().toString().substring(0, 6));
        orderProductService.createOrderProducts(cart.getCartProducts());
        order = orderRepository.save(order);
        return toResponse(orderRepository.findCartByCustomerId(order.getId()));

    }

    @Override
    public Page<OrderResponse> getOrders(Pageable pageable) {
        var customer = customerService.getCustomerByUserId(SecurityContext.getUserId());
        return toResponseList(orderRepository.findOrdersByCustomerId(customer.getId(), pageable));
    }

    @Override
    public OrderResponse getOrderByCode(String code) {
        return toResponse(orderRepository.findOrderByCode(code));
    }

    private Page<OrderResponse> toResponseList(Page<OrderProjection> ordersByCustomerId) {
        return ordersByCustomerId.map(this::toResponse);
    }

    private OrderResponse toResponse(OrderProjection orderProjection) {
        return OrderResponse.builder()
                .id(orderProjection.getId())
                .code(orderProjection.getCode())
                .price(orderProjection.getPrice())
                .orderProducts(
                        ObjectConverter.readValue(
                                orderProjection.getOrderProducts(), new TypeReference<List<OrderProductResponse>>() {
                                }
                        ).orElse(List.of())
                )
                .build();
    }

}
