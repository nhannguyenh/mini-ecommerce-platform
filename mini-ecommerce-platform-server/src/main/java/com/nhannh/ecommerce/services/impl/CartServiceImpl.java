package com.nhannh.ecommerce.services.impl;

import com.nhannh.ecommerce.domain.CartStatus;
import com.nhannh.ecommerce.domain.dtos.AddCartItemRequestDto;
import com.nhannh.ecommerce.domain.dtos.CartDto;
import com.nhannh.ecommerce.domain.dtos.CartItemDto;
import com.nhannh.ecommerce.domain.dtos.ProductDto;
import com.nhannh.ecommerce.domain.entities.Cart;
import com.nhannh.ecommerce.mappers.CartMapper;
import com.nhannh.ecommerce.repositories.CartRepository;
import com.nhannh.ecommerce.services.CartItemService;
import com.nhannh.ecommerce.services.CartService;
import com.nhannh.ecommerce.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartMapper cartMapper;
    private final CartRepository cartRepository;

    private final ProductService productService;
    private final CartItemService cartItemService;

    @Override
    public CartDto getCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> cartRepository.save(
                        Cart.builder()
                                .userId(userId)
                                .status(CartStatus.ACTIVE)
                                .totalPrice(0.0)
                                .build()
                ));
        return cartMapper.mapToDto(cart);
    }

    @Override
    @Transactional
    public CartDto addItems(Long userId, AddCartItemRequestDto itemRequestDto) {
        // check cart is existed or not
        CartDto cartDto = getCart(userId);

        // check product is existed or not
        ProductDto productDto = productService.getProductById(itemRequestDto.getProductId());

        // check and update items
        List<CartItemDto> items = cartItemService.findByCartId(cartDto.getId());
        double totalPrice = 0;
        if (items.isEmpty()) {
            // Create item
            CartItemDto cartItemDto = CartItemDto.builder()
                    .cartId(cartDto.getId())
                    .productId(itemRequestDto.getProductId())
                    .quantity(itemRequestDto.getQuantity())
                    .price(productDto.getPrice())
                    .build();
            cartItemService.addCartItem(cartItemDto);
        } else {
            for (CartItemDto item : items) {
                if (itemRequestDto.getProductId().equals(item.getProductId())) {
                    item.setQuantity(itemRequestDto.getQuantity());
                    cartItemService.addCartItem(item);
                }
                totalPrice += item.getQuantity() * item.getPrice();
            }
        }
        cartDto.setItems(items.stream()
                .map(CartItemDto::getId)
                .collect(Collectors.toSet())
        );
        cartDto.setTotalPrice(totalPrice);
        return cartDto;
    }
}
