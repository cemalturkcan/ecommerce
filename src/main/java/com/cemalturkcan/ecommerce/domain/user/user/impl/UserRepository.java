package com.cemalturkcan.ecommerce.domain.user.user.impl;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserById(Long id);

    Optional<User> getUserByEmail(String email);

}
