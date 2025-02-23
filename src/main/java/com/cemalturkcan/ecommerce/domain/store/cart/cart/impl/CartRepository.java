package com.cemalturkcan.ecommerce.domain.store.cart.cart.impl;

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
            WHERE c.customer_id = :customerId AND c.status = :cartStatus
            GROUP BY c.id
            ORDER BY c.id DESC
            LIMIT 1
            """, nativeQuery = true)
    CartProjection findCartByCustomerId(Long customerId, String cartStatus);


    @Modifying
    @Query(value = """
            UPDATE cart
            SET price = price + :price
            WHERE customer_id = :customerId AND status = 'ACTIVE'
            """, nativeQuery = true)
    void updateCartPriceBySum(Long customerId, Double price);

    @Modifying
    @Query(value = """
            UPDATE cart
            SET price = :price
            WHERE customer_id = :customerId AND status = 'ACTIVE'
            """, nativeQuery = true)
    void updateCartPrice(Long customerId, Double price);


    @Modifying
    @Query(value = """
            UPDATE cart
            SET status = :cartStatus
            WHERE customer_id = :customerId AND status = 'ACTIVE'
            """, nativeQuery = true)
    void updateCartStatus(Long customerId, String cartStatus);

    @Query(value = """
            SELECT c.id
            FROM cart c
            WHERE c.customer_id = :customerId AND c.status = :cartStatus
            ORDER BY c.id DESC
            LIMIT 1
            """, nativeQuery = true)
    Long findCartByCustomerIdGetId(Long customerId, String cartStatus);


    @Query(value = """
            SELECT
                EXISTS(
                    SELECT 1
                    FROM cart c
                    JOIN cart_product cp ON c.id = cp.cart_id
                    WHERE c.customer_id = :customerId AND c.status = 'ACTIVE'
                    ORDER BY c.id DESC
                )
            """, nativeQuery = true)
    Boolean anyProductInCart(Long customerId);

}
