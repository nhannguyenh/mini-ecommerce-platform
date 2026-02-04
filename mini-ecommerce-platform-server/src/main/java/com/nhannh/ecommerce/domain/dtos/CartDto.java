package com.nhannh.ecommerce.domain.dtos;

import com.nhannh.ecommerce.domain.CartStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {
    private Long id;
    private Long userId;
    private CartStatus status;
    private Double totalPrice;
    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;
}
