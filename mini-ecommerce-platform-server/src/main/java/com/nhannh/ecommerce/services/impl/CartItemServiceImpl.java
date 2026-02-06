package com.nhannh.ecommerce.services.impl;

import com.nhannh.ecommerce.domain.dtos.CartItemDto;
import com.nhannh.ecommerce.domain.entities.CartItem;
import com.nhannh.ecommerce.mappers.CartItemMapper;
import com.nhannh.ecommerce.repositories.CartItemRepository;
import com.nhannh.ecommerce.services.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final CartItemMapper cartItemMapper;
    private final CartItemRepository cartItemRepository;

    @Override
    public CartItemDto addOrUpdateCartItem(CartItemDto cartItemDto) {
        CartItem cartItem = cartItemRepository.save(cartItemMapper.mapToEntity(cartItemDto));
        return cartItemMapper.mapToDto(cartItem);
    }

    @Override
    public List<CartItemDto> findByCartId(Long cartId) {
        return cartItemRepository.findByCartId(cartId).stream()
                .map(cartItemMapper::mapToDto)
                .toList();
    }
}
