package com.cemalturkcan.ecommerce.domain.store.cart.cartproduct.impl;

import com.cemalturkcan.ecommerce.library.entity.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = CartProduct.TABLE_NAME)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartProduct extends AbstractEntity {
    public static final String TABLE_NAME = "cart_product";
    private static final String COL_NAME = "cart_id";
    private static final String COL_PRODUCT_ID = "product_id";
    private static final String COL_QUANTITY = "quantity";

    @Column(name = COL_NAME)
    private Long cartId;

    @Column(name = COL_PRODUCT_ID)
    private Long productId;

    @Column(name = COL_QUANTITY)
    private Integer quantity;
}
