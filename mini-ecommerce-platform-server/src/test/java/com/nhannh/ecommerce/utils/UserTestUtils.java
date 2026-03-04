package com.nhannh.ecommerce.utils;

import com.nhannh.ecommerce.domain.UserRole;
import com.nhannh.ecommerce.domain.dtos.users.UserDto;
import com.nhannh.ecommerce.domain.entities.User;

public class UserTestUtils {

    public static UserDto generateUserDto(String email, String password) {
        return UserDto.builder()
                .email(email)
                .password(password)
                .role(UserRole.USER)
                .build();
    }

    public static User generateUser(Long id, String email, String password) {
        return User.builder()
                .id(id)
                .email(email)
                .password(password)
                .role(UserRole.USER)
                .build();
    }
}
