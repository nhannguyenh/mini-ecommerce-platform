package com.nhannh.ecommerce.services;

import com.nhannh.ecommerce.domain.dtos.CartItemDto;
import com.nhannh.ecommerce.domain.entities.CartItem;
import com.nhannh.ecommerce.mappers.CartItemMapper;
import com.nhannh.ecommerce.repositories.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemService {
    private final CartItemMapper cartItemMapper;
    private final CartItemRepository cartItemRepository;

    @Transactional
    public CartItemDto addOrUpdateCartItem(CartItemDto cartItemDto) {
        CartItem cartItem = cartItemRepository.save(cartItemMapper.mapToEntity(cartItemDto));
        return cartItemMapper.mapToDto(cartItem);
    }

    @Transactional
    public List<CartItemDto> findByCartId(Long cartId) {
        return cartItemRepository.findByCartId(cartId).stream()
                .map(cartItemMapper::mapToDto)
                .toList();
    }
}
