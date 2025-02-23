package com.cemalturkcan.ecommerce.domain.store.order.orderproduct.impl;

import com.cemalturkcan.ecommerce.domain.store.cart.cartproduct.web.CartProductResponse;
import com.cemalturkcan.ecommerce.domain.store.order.orderproduct.api.OrderProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderProductServiceImpl implements OrderProductService {
    private final OrderProductRepository orderProductRepository;


    @Override
    public void createOrderProducts(List<CartProductResponse> cartProducts) {
        cartProducts.forEach(cartProduct -> {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setCartProductId(cartProduct.getId());
            orderProduct.setPrice(cartProduct.getProduct().getPrice());
            orderProductRepository.save(orderProduct);
        });
    }
}
