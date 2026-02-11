package com.nhannh.ecommerce.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {
    private Long id;
    private Long orderId;
    private Long productId;
    private String productName;
    private Double productPrice;
    private Integer quantity;
    private Double subTotal;
    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;
}
