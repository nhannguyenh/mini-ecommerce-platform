package com.nhannh.ecommerce.domain.dtos;

import com.nhannh.ecommerce.domain.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String email;
    private String password;
    private UserRole role;
    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;
}
