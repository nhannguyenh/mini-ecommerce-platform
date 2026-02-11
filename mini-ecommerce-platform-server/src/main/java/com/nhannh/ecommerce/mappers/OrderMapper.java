package com.nhannh.ecommerce.mappers;

import com.nhannh.ecommerce.domain.dtos.OrderDto;
import com.nhannh.ecommerce.domain.entities.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public Order mapToEntity(OrderDto orderDto) {
        return Order.builder()
                .id(orderDto.getId())
                .userId(orderDto.getUserId())
                .status(orderDto.getStatus())
                .totalPrice(orderDto.getTotalPrice())
                .createdOn(orderDto.getCreatedOn())
                .modifiedOn(orderDto.getModifiedOn())
                .build();
    }

    public OrderDto mapToDto(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .userId(order.getUserId())
                .status(order.getStatus())
                .totalPrice(order.getTotalPrice())
                .createdOn(order.getCreatedOn())
                .modifiedOn(order.getModifiedOn())
                .build();
    }
}
