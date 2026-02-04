package com.nhannh.ecommerce.services.impl;

import com.nhannh.ecommerce.domain.dtos.CartDto;
import com.nhannh.ecommerce.domain.entities.Cart;
import com.nhannh.ecommerce.mappers.CartMapper;
import com.nhannh.ecommerce.repositories.CartRepository;
import com.nhannh.ecommerce.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartMapper cartMapper;
    private final CartRepository cartRepository;

    @Override
    public Cart createCart(CartDto cartDto) {
        return cartRepository.save(cartMapper.mapToEntity(cartDto));
    }
}
