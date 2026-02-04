package com.nhannh.ecommerce.services;

import com.nhannh.ecommerce.domain.dtos.AddCartItemRequestDto;
import com.nhannh.ecommerce.domain.dtos.CartDto;

public interface CartService {
    CartDto getCart(Long userId);
    CartDto addItems(Long userId, AddCartItemRequestDto addCartItemRequestDto);
}
