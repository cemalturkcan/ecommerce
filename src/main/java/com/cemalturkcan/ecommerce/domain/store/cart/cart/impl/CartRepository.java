package com.cemalturkcan.ecommerce.domain.store.cart.cart.impl;

import com.cemalturkcan.ecommerce.domain.store.cart.cart.impl.projection.CartProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CartRepository extends JpaRepository<Cart, Long> {


    @Query(value = """
            SELECT
                c.id AS id,
                c.price AS price,
                COALESCE(
                    json_agg(
                        json_build_object(
                            'id', cp.id,
                            'quantity', cp.quantity,
                            'product', json_build_object(
                                'id', p.id,
                                'name', p.name,
                                'price', p.price,
                                'stock', p.stock
                            )
                        )
                    ) FILTER (WHERE cp.id IS NOT NULL), '[]'
                ) AS cart_products
            FROM cart c
            LEFT JOIN cart_product cp ON c.id = cp.cart_id
            LEFT JOIN product p ON cp.product_id = p.id
            WHERE c.customer_id = :customerId
            GROUP BY c.id, c.price
            """, nativeQuery = true)
    CartProjection findCartByCustomerId(Long customerId);


    @Modifying
    @Query(value = """
            UPDATE cart
            SET price = price + :price
            WHERE customer_id = :customerId
            """, nativeQuery = true)
    void updateCartPriceBySum(Long customerId, Double price);

    @Modifying
    @Query(value = """
            UPDATE cart
            SET price = :price
            WHERE customer_id = :customerId
            """, nativeQuery = true)
    void updateCartPrice(Long customerId, Double price);

}
