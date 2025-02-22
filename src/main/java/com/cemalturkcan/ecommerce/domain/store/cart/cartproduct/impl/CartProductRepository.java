package com.cemalturkcan.ecommerce.domain.store.cart.cartproduct.impl;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartProductRepository extends JpaRepository<CartProduct, Long> {

    Optional<CartProduct> findByCartIdAndProductId(Long cartId, Long productId);

    List<CartProduct> findByCartId(Long cartId);

}
