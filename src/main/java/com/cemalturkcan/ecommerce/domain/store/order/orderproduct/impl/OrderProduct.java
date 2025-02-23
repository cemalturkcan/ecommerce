package com.cemalturkcan.ecommerce.domain.store.order.orderproduct.impl;

import com.cemalturkcan.ecommerce.library.entity.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = OrderProduct.TABLE_NAME)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderProduct extends AbstractEntity {
    public static final String TABLE_NAME = "ordr_product";
    private static final String COL_CART_PRODUCT_ID = "cart_product_id";
    private static final String COL_PRICE = "price";

    @Column(name = COL_CART_PRODUCT_ID)
    private Long cartProductId;

    @Column(name = COL_PRICE)
    private Double price;
}
