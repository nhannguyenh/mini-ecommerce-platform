package com.nhannh.ecommerce.domain.dtos;

import com.nhannh.ecommerce.domain.entities.Cart;
import com.nhannh.ecommerce.domain.entities.Product;
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
    private Cart cart;
    private Product product;
    private Integer quantity;
    private Double price;
    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;
}
