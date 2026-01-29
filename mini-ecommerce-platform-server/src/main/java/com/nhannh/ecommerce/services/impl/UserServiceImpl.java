package com.nhannh.ecommerce.services.impl;

import com.nhannh.ecommerce.domain.dtos.UserDto;
import com.nhannh.ecommerce.domain.entities.User;
import com.nhannh.ecommerce.mappers.UserMapper;
import com.nhannh.ecommerce.repositories.UserRepository;
import com.nhannh.ecommerce.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public User registerUser(UserDto userDto) {
        String email = userDto.getEmail();
        String existedEmailMessage = String.format(
                "Email: %s is existed, please check and use a different email to create user",
                email
        );

        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            log.warn(existedEmailMessage);
            throw new IllegalArgumentException(existedEmailMessage);
        }
        return userRepository.save(userMapper.mapToUser(userDto));
    }
}
