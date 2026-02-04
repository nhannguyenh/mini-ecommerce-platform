package com.nhannh.ecommerce.mappers;

import com.nhannh.ecommerce.domain.dtos.CartItemDto;
import com.nhannh.ecommerce.domain.entities.CartItem;
import org.springframework.stereotype.Component;

@Component
public class CartItemMapper {

    public CartItem mapToEntity(CartItemDto cartItemDto) {
        return CartItem.builder()
                .id(cartItemDto.getId())
                .cart(cartItemDto.getCart())
                .product(cartItemDto.getProduct())
                .price(cartItemDto.getPrice())
                .quantity(cartItemDto.getQuantity())
                .createdOn(cartItemDto.getCreatedOn())
                .modifiedOn(cartItemDto.getModifiedOn())
                .build();
    }

    public CartItemDto mapToDto(CartItem cartItem) {
        return CartItemDto.builder()
                .id(cartItem.getId())
                .cart(cartItem.getCart())
                .product(cartItem.getProduct())
                .price(cartItem.getPrice())
                .quantity(cartItem.getQuantity())
                .createdOn(cartItem.getCreatedOn())
                .modifiedOn(cartItem.getModifiedOn())
                .build();
    }
}
