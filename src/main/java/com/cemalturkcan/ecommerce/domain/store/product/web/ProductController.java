package com.cemalturkcan.ecommerce.domain.store.product.web;

import com.cemalturkcan.ecommerce.domain.store.product.api.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping
    public void create() {

    }


}
