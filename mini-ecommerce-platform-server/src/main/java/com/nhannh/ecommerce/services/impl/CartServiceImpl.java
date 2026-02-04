package com.nhannh.ecommerce.services.impl;

import com.nhannh.ecommerce.domain.CartStatus;
import com.nhannh.ecommerce.domain.dtos.AddCartItemRequestDto;
import com.nhannh.ecommerce.domain.entities.Cart;
import com.nhannh.ecommerce.domain.entities.CartItem;
import com.nhannh.ecommerce.domain.entities.Product;
import com.nhannh.ecommerce.repositories.CartItemRepository;
import com.nhannh.ecommerce.repositories.CartRepository;
import com.nhannh.ecommerce.repositories.ProductRepository;
import com.nhannh.ecommerce.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    @Transactional
    public Cart addItems(Long userId, AddCartItemRequestDto addCartItemRequestDto) {
        // get or create cart if it's not exist
        Cart cart = getOrCreateCart(userId);

        Product product = productRepository.findById(addCartItemRequestDto.getProductId())
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("Cannot found the product with id: %d", addCartItemRequestDto.getProductId())
                ));

        Integer quantity = addCartItemRequestDto.getQuantity();
        CartItem cartItemSaved = cartItemRepository.save(
                CartItem.builder()
                    .cart(cart)
                    .product(product)
                    .quantity(quantity)
                    .price(product.getPrice() * quantity)
                    .build()
        );

        List<CartItem> items = cart.getItems();
        items.add(cartItemSaved);

        cart.setItems(items);
        return cartRepository.save(cart);
    }

    private Cart getOrCreateCart(Long userId) {
        return cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    return cartRepository.save(
                            Cart.builder()
                                    .userId(userId)
                                    .status(CartStatus.ACTIVE)
                                    .totalPrice(0.0)
                                    .build()
                    );
                });
    }
}
