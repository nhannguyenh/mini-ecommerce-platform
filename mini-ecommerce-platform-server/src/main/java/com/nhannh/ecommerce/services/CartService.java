package com.nhannh.ecommerce.services;

import com.nhannh.ecommerce.domain.dtos.CartDto;
import com.nhannh.ecommerce.domain.dtos.CartItemRequestDto;
import com.nhannh.ecommerce.domain.dtos.ProductDto;

public interface CartService {
    CartDto getCart(Long userId);
    CartDto addItems(Long userId, CartItemRequestDto addCartItemRequestDto);
    void validate(ProductDto productDto, CartItemRequestDto itemRequestDto);
}
