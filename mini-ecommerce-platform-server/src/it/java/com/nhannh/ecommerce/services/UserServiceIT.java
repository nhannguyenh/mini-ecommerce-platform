package com.nhannh.ecommerce.services;

import com.nhannh.ecommerce.AbstractIntegrationTest;
import com.nhannh.ecommerce.domain.UserRole;
import com.nhannh.ecommerce.domain.dtos.users.UserDto;
import com.nhannh.ecommerce.domain.dtos.users.UserResponseDto;
import com.nhannh.ecommerce.repositories.UserRepository;
import com.nhannh.ecommerce.utils.UserUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceIT extends AbstractIntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void shouldCreateUser_whenRequestInputValid() {
        String email = "test@local.dev";
        UserDto userInput = UserUtils.generateUserDto(email, "password");

        UserResponseDto userSaved = userService.registerUser(userInput);

        assertNotNull(userSaved.getId());
        assertEquals(email, userSaved.getEmail());
        assertEquals(UserRole.USER, userSaved.getRole());
    }

    @Test
    void shouldThrowException_whenEmailExisted() {
        String email = "existed@local.dev";
        String existedEmailMessage = String.format(
                "Email: %s is existed, please check and use a different email to create user",
                email
        );

        userRepository.save(UserUtils.generateUser(
                null,
                email,
                "$2a$10$cb.KYABzfcZSqTwDzvqsBe9cuE7sDH/F5TMOJVuyvann492vm6Xgm")
        );
        UserDto userInput = UserUtils.generateUserDto(email, "password");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> userService.registerUser(userInput)
        );
        assertEquals(existedEmailMessage, exception.getMessage());
    }
}
