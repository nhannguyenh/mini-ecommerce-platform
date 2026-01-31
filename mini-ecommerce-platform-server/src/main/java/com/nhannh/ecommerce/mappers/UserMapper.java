package com.nhannh.ecommerce.mappers;

import com.nhannh.ecommerce.domain.UserRole;
import com.nhannh.ecommerce.domain.dtos.UserDto;
import com.nhannh.ecommerce.domain.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final PasswordEncoder passwordEncoder;

    public User mapToUser(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .role(UserRole.USER)
                .createdOn(userDto.getCreatedOn())
                .modifiedOn(userDto.getModifiedOn())
                .build();
    }
}
