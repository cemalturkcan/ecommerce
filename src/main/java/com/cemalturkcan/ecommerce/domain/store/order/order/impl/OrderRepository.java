package com.cemalturkcan.ecommerce.domain.store.order.order.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {


    @Query(value = """
            SELECT
                o.id AS id,
                o.code AS code,
                c.price AS price,
                COALESCE(
                    json_agg(
                        json_build_object(
                            'id', cp.id,
                            'quantity', cp.quantity,
                            'price', op.price,
                            'product', json_build_object(
                                'id', p.id,
                                'name', p.name,
                                'price', p.price,
                                'stock', p.stock
                            )
                        )
                    ) FILTER (WHERE cp.id IS NOT NULL), '[]'
                ) AS order_products
            FROM ordr o
            LEFT JOIN cart c ON c.id = o.cart_id
            LEFT JOIN cart_product cp ON cp.cart_id = c.id
            LEFT JOIN product p ON p.id = cp.product_id
            LEFT JOIN ordr_product op ON op.cart_product_id = cp.id
            WHERE o.id = :orderId
            GROUP BY c.id, o.id
            """, nativeQuery = true)
    OrderProjection findCartByCustomerId(Long orderId);


    @Query(value = """
            SELECT
                            o.id AS id,
                            o.code AS code,
                            c.price AS price,
                            COALESCE(
                                json_agg(
                                    json_build_object(
                                        'id', cp.id,
                                        'quantity', cp.quantity,
                                        'price', op.price,
                                        'product', json_build_object(
                                            'id', p.id,
                                            'name', p.name,
                                            'price', p.price,
                                            'stock', p.stock
                                        )
                                    )
                                ) FILTER (WHERE cp.id IS NOT NULL), '[]'
                            ) AS order_products
                        from cart c
                        LEFT JOIN ordr o ON o.cart_id = c.id
                        LEFT JOIN cart_product cp ON cp.cart_id = c.id
                        LEFT JOIN product p ON p.id = cp.product_id
                        LEFT JOIN ordr_product op ON op.cart_product_id = cp.id
                        WHERE customer_id = :id and status = 'INACTIVE'
                        GROUP BY o.id, c.id
            """, nativeQuery = true)
    Page<OrderProjection> findOrdersByCustomerId(Long id, Pageable pageable);
}
