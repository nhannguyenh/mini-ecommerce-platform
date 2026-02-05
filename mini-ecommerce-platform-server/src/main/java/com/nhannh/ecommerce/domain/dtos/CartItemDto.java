package com.nhannh.ecommerce.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {
    private Long id;
    private Long cartId;
    private Long productId;
    private Integer quantity;
    private Double price;
    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;
}
