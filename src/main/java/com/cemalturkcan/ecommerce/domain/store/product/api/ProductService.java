package com.cemalturkcan.ecommerce.domain.store.product.api;

import com.cemalturkcan.ecommerce.domain.store.product.web.ProductRequest;
import com.cemalturkcan.ecommerce.domain.store.product.web.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<ProductResponse> getProducts(Pageable pageable);

    ProductResponse createProduct(ProductRequest request);

    ProductResponse updateProduct(ProductRequest request, Long id);

    void deleteProduct(Long id);

    ProductResponse checkStockAndReduceOrIncreaseStock(Long productId, int quantity);

    void increaseStock(Long productId, Integer quantity);
}