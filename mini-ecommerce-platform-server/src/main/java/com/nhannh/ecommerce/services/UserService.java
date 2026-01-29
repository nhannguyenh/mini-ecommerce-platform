package com.nhannh.ecommerce.services;

import com.nhannh.ecommerce.domain.dtos.UserDto;
import com.nhannh.ecommerce.domain.entities.User;

public interface UserService {
    User registerUser(UserDto userDto);
}
