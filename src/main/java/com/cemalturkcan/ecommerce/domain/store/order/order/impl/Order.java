package com.cemalturkcan.ecommerce.domain.store.order.order.impl;

import com.cemalturkcan.ecommerce.library.entity.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = Order.TABLE_NAME)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Order extends AbstractEntity {
    public static final String TABLE_NAME = "ordr";
    private static final String COL_CART_ID = "cart_id";
    private static final String COL_CODE = "code";

    @Column(name = COL_CART_ID)
    private Long cartId;

    @Column(name = COL_CODE)
    private String code;

}
