package com.nhannh.ecommerce.services.impl;

import com.nhannh.ecommerce.domain.dtos.CartItemDto;
import com.nhannh.ecommerce.domain.entities.CartItem;
import com.nhannh.ecommerce.mappers.CartItemMapper;
import com.nhannh.ecommerce.repositories.CartItemRepository;
import com.nhannh.ecommerce.services.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final CartItemMapper cartItemMapper;
    private final CartItemRepository cartItemRepository;

    @Override
    public CartItemDto addCartItem(CartItemDto cartItemDto) {
        CartItem cartItem = cartItemRepository.save(cartItemMapper.mapToEntity(cartItemDto));
        return cartItemMapper.mapToDto(cartItem);
    }
}
