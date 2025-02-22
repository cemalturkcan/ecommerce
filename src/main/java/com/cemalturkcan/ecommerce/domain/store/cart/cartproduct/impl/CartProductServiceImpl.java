package com.cemalturkcan.ecommerce.domain.store.cart.cartproduct.impl;

import com.cemalturkcan.ecommerce.domain.store.cart.cartproduct.api.CartProductService;
import com.cemalturkcan.ecommerce.domain.store.product.api.ProductService;
import com.cemalturkcan.ecommerce.domain.store.product.web.ProductResponse;
import com.cemalturkcan.ecommerce.library.enums.MessageCodes;
import com.cemalturkcan.ecommerce.library.exception.CoreException;
import com.cemalturkcan.ecommerce.library.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartProductServiceImpl implements CartProductService {
    private final CartProductRepository cartProductRepository;
    private final ProductService productService;

    @Override
    @Transactional
    public Double addOrRemoveProductToCart(Long id, Long productId, int quantity) {
        ProductResponse product = productService.checkStockAndReduceOrIncreaseStock(productId, quantity);

        cartProductRepository.findByCartIdAndProductId(id, productId)
                .ifPresentOrElse(
                        cp -> {
                            var quantityAfterAddition = cp.getQuantity() + quantity;
                            if (quantityAfterAddition < 0) {
                                throw new CoreException(MessageCodes.PRODUCT_QUANTITY_CANNOT_BE_NEGATIVE, Constants.CART_PRODUCT_EXCEPTION, productId);
                            }
                            else if (quantityAfterAddition == 0) {
                                cartProductRepository.delete(cp);
                                return;
                            }

                            cp.setQuantity(cp.getQuantity() + quantity);
                            cartProductRepository.save(cp);
                        },
                        () -> {
                            if (quantity < 0) {
                                throw new CoreException(MessageCodes.PRODUCT_NOT_FOUND_IN_CART, Constants.CART_PRODUCT_EXCEPTION, productId);
                            }
                            cartProductRepository.save(createCartProduct(id, productId, quantity));
                        }
                );

        return product.getPrice() * quantity;
    }

    @Override
    @Transactional
    public void emptyCart(Long id) {
        var cartProducts = cartProductRepository.findByCartId(id);
        cartProducts.forEach(cp -> productService.increaseStock(cp.getProductId(), cp.getQuantity()));
        cartProductRepository.deleteAll(cartProducts);
    }

    private CartProduct createCartProduct(Long id, Long productId, int quantity) {
        CartProduct cartProduct = new CartProduct();
        cartProduct.setCartId(id);
        cartProduct.setProductId(productId);
        cartProduct.setQuantity(quantity);
        return cartProduct;
    }

}
