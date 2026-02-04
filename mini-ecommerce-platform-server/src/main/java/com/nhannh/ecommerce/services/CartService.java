package com.nhannh.ecommerce.services;

import com.nhannh.ecommerce.domain.dtos.AddCartItemRequestDto;
import com.nhannh.ecommerce.domain.entities.Cart;

public interface CartService {
    Cart addItems(Long userId, AddCartItemRequestDto addCartItemRequestDto);
}
