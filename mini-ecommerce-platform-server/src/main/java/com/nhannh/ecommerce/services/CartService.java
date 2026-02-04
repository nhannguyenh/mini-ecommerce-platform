package com.nhannh.ecommerce.services;

import com.nhannh.ecommerce.domain.dtos.CartDto;
import com.nhannh.ecommerce.domain.entities.Cart;

public interface CartService {
    Cart createCart(CartDto cartDto);
}
