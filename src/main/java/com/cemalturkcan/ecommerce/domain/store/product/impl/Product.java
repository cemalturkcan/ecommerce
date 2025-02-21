package com.cemalturkcan.ecommerce.domain.store.product.impl;

import com.cemalturkcan.ecommerce.library.entity.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = Product.TABLE_NAME)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends AbstractEntity {
    public static final String TABLE_NAME = "products";
    public static final String COL_NAME = "name";
    public static final String COL_PRICE = "price";
    public static final String COL_STOCK = "stock";

    @Column(name = COL_NAME)
    private String name;

    @Column(name = COL_PRICE)
    private Double price;

    @Column(name = COL_STOCK)
    private Integer stock;
}