package com.cemalturkcan.ecommerce.domain.user.customer.impl;

import com.cemalturkcan.ecommerce.library.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = Customer.TABLE_NAME)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends AbstractEntity {
    public static final String TABLE_NAME = "customers";
    public static final String COL_NAME = "name";
    public static final String COL_SURNAME = "surname";
    public static final String COL_USER_ID = "user_id";

    @Column(name = COL_NAME)
    private String name;

    @Column(name = COL_SURNAME)
    private String surname;


    @Column(name = COL_USER_ID)
    private Long userId;
}
