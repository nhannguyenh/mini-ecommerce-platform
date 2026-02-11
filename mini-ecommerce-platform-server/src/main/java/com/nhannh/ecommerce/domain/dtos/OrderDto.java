package com.nhannh.ecommerce.domain.dtos;

import com.nhannh.ecommerce.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private Long userId;
    private OrderStatus status;
    private Double totalPrice;
    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;
}
