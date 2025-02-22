package com.cemalturkcan.ecommerce.domain.store.cart.cart.impl;

import com.cemalturkcan.ecommerce.library.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = Cart.TABLE_NAME)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Cart extends AbstractEntity {
    public static final String TABLE_NAME = "cart";
    private static final String COL_CUSTOMER_ID = "customer_id";
    private static final String COL_PRICE = "price";
    private static final String COL_STATUS = "status";

    @Column(name = COL_CUSTOMER_ID)
    private Long customerId;

    @Column(name = COL_PRICE)
    private Double price;

    @Column(name = COL_STATUS)
    @Enumerated(EnumType.STRING)
    private CartStatus status;
}