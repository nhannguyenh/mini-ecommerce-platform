package com.nhannh.ecommerce.services;

import com.nhannh.ecommerce.domain.dtos.CartDto;
import com.nhannh.ecommerce.domain.dtos.CartItemRequestDto;

public interface CartService {
    CartDto getCart(Long userId);
    CartDto addOrUpdateCartItems(Long userId, CartItemRequestDto addCartItemRequestDto);
}
