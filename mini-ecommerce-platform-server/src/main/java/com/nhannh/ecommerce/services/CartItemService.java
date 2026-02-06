package com.nhannh.ecommerce.services;

import com.nhannh.ecommerce.domain.dtos.CartItemDto;

import java.util.List;

public interface CartItemService {
    CartItemDto addOrUpdateCartItem(CartItemDto cartItemDto);
    List<CartItemDto> findByCartId(Long cartId);
}
