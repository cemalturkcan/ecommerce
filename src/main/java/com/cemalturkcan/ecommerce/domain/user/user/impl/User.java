package com.cemalturkcan.ecommerce.domain.user.user.impl;

import com.cemalturkcan.ecommerce.library.entity.AbstractEntity;
import com.cemalturkcan.ecommerce.library.security.jwt.api.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = User.TABLE_NAME)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbstractEntity {
    public static final String TABLE_NAME = "users";
    public static final String COL_EMAIL = "email";
    public static final String COL_PASSWORD = "password";
    public static final String COL_ROLE = "role";
    public static final String COL_STATUS = "status";

    @Column(name = COL_EMAIL, unique = true, nullable = false)
    private String email;

    @Column(name = COL_PASSWORD)
    private String password;

    @Column(name = COL_ROLE)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(name = COL_STATUS)
    private Boolean status;
}
