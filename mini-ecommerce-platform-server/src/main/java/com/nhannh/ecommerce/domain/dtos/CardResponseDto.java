package com.nhannh.ecommerce.domain.dtos;

import com.nhannh.ecommerce.domain.CartStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardResponseDto {
    private Long id;
    private Long userId;
    private CartStatus status;
    private Double totalPrice;
    private List<Long> items;
}
