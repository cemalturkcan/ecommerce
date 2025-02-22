package com.cemalturkcan.ecommerce.domain.store.product.impl;

import com.cemalturkcan.ecommerce.domain.store.product.api.ProductService;
import com.cemalturkcan.ecommerce.domain.store.product.web.ProductRequest;
import com.cemalturkcan.ecommerce.domain.store.product.web.ProductResponse;
import com.cemalturkcan.ecommerce.library.enums.MessageCodes;
import com.cemalturkcan.ecommerce.library.exception.CoreException;
import com.cemalturkcan.ecommerce.library.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Page<ProductResponse> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable).map(this::toResponse);
    }

    @Override
    @Transactional
    public ProductResponse createProduct(ProductRequest request) {
        Product product = productRepository.save(toEntity(new Product(), request));
        return toResponse(product);
    }

    @Override
    @Transactional
    public ProductResponse updateProduct(ProductRequest request, Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new CoreException(MessageCodes.ENTITY_NOT_FOUND, Constants.PRODUCT_EXCEPTION, id));
        product = productRepository.save(toEntity(product, request));
        return toResponse(product);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }

    @Override
    @Transactional
    public ProductResponse checkStockAndReduceOrIncreaseStock(Long productId, int quantity) {
        Product product = getProductById(productId);
        if (product.getStock() < quantity) {
            throw new CoreException(MessageCodes.PRODUCT_STOCK_NOT_ENOUGH, Constants.PRODUCT_EXCEPTION, productId);
        }
        product.setStock(product.getStock() - quantity);
        productRepository.save(product);
        return toResponse(product);
    }

    @Override
    public void increaseStock(Long productId, Integer quantity) {
        Product product = getProductById(productId);
        product.setStock(product.getStock() + quantity);
        productRepository.save(product);
    }


    private Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new CoreException(MessageCodes.ENTITY_NOT_FOUND, Constants.PRODUCT_EXCEPTION, productId));
    }


    private ProductResponse toResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .build();
    }

    private Product toEntity(Product product, ProductRequest request) {
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        return product;
    }


}