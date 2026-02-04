package com.nhannh.ecommerce.services;

import com.nhannh.ecommerce.domain.dtos.CartItemDto;

public interface CartItemService {
    CartItemDto addCartItem(CartItemDto cartItemDto);
}
