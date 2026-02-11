package com.nhannh.ecommerce.mappers;

import com.nhannh.ecommerce.domain.dtos.OrderItemDto;
import com.nhannh.ecommerce.domain.entities.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {

    public OrderItem mapToEntity(OrderItemDto orderItemDto) {
        return OrderItem.builder()
                .orderId(orderItemDto.getOrderId())
                .productId(orderItemDto.getProductId())
                .productName(orderItemDto.getProductName())
                .productPrice(orderItemDto.getProductPrice())
                .quantity(orderItemDto.getQuantity())
                .subTotal(orderItemDto.getSubTotal())
                .createdOn(orderItemDto.getCreatedOn())
                .modifiedOn(orderItemDto.getModifiedOn())
                .build();
    }
}
