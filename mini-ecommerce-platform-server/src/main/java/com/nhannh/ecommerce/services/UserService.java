package com.nhannh.ecommerce.services;

import com.nhannh.ecommerce.domain.dtos.users.UserDto;
import com.nhannh.ecommerce.domain.dtos.users.UserResponseDto;
import com.nhannh.ecommerce.domain.entities.User;
import com.nhannh.ecommerce.mappers.UserMapper;
import com.nhannh.ecommerce.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Transactional
    public UserResponseDto registerUser(UserDto userDto) {
        String email = userDto.getEmail();
        String existedEmailMessage = String.format(
                "Email: %s is existed, please check and use a different email to create user",
                email
        );

        if (userRepository.findByEmail(email).isPresent()) {
            log.warn(existedEmailMessage);
            throw new IllegalArgumentException(existedEmailMessage);
        }
        User savedUser = userRepository.save(userMapper.mapToUser(userDto));
        return userMapper.mapToUserResponseDto(savedUser);
    }
}
