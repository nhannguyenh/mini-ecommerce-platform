package com.nhannh.ecommerce.domain.dtos.users;

import com.nhannh.ecommerce.domain.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private Long id;
    private String email;
    private UserRole role;
}
