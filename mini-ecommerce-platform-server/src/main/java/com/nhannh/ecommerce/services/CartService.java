package com.nhannh.ecommerce.services;

import com.nhannh.ecommerce.domain.dtos.AddCartItemRequestDto;
import com.nhannh.ecommerce.domain.dtos.CartDto;
import com.nhannh.ecommerce.domain.entities.Cart;

public interface CartService {
    CartDto getCart(Long userId);
    Cart addItems(Long userId, AddCartItemRequestDto addCartItemRequestDto);
}
