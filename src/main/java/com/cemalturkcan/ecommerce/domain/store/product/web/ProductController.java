package com.cemalturkcan.ecommerce.domain.store.product.web;

import com.cemalturkcan.ecommerce.domain.store.product.api.ProductService;
import com.cemalturkcan.ecommerce.library.rest.Response;
import com.cemalturkcan.ecommerce.library.rest.ResponseBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/filter")
    public Response<Page<ProductResponse>> getProducts(Pageable pageable) {
        return ResponseBuilder.build(productService.getProducts(pageable));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Response<ProductResponse> createProduct(@Valid @RequestBody ProductRequest request) {
        return ResponseBuilder.build(productService.createProduct(request));
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Response<ProductResponse> updateProduct(@Valid @RequestBody ProductRequest request, @PathVariable Long id) {
        return ResponseBuilder.build(productService.updateProduct(request, id));
    }


    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Response<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseBuilder.buildSuccessResponse();
    }

}
