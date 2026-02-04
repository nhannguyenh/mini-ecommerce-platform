package com.nhannh.ecommerce.mappers;

import com.nhannh.ecommerce.domain.dtos.CartDto;
import com.nhannh.ecommerce.domain.entities.Cart;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {

    public Cart mapToEntity(CartDto cartDto) {
        return Cart.builder()
                .id(cartDto.getId())
                .userId(cartDto.getUserId())
                .status(cartDto.getStatus())
                .totalPrice(cartDto.getTotalPrice())
                .createdOn(cartDto.getCreatedOn())
                .modifiedOn(cartDto.getModifiedOn())
                .build();
    }
}
