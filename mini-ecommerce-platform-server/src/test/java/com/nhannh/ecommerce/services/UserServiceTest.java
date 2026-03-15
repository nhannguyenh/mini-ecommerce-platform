package com.nhannh.ecommerce.services;

import com.nhannh.ecommerce.domain.UserRole;
import com.nhannh.ecommerce.domain.dtos.users.UserDto;
import com.nhannh.ecommerce.domain.dtos.users.UserResponseDto;
import com.nhannh.ecommerce.domain.entities.User;
import com.nhannh.ecommerce.mappers.UserMapper;
import com.nhannh.ecommerce.repositories.UserRepository;
import com.nhannh.ecommerce.utils.UserTestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserMapper userMapper;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldCreateUser_whenRequestValid_thenReturnNewUser() {
        Long id = 1L;
        String email = "test@local.dev";
        String password = "$2a$10$cb.KYABzfcZSqTwDzvqsBe9cuE7sDH/F5TMOJVuyvann492vm6Xgm";

        UserDto userInput = UserTestUtils.generateUserDto(email, "password");
        User userToSave = UserTestUtils.generateUser(null, email, password);
        User savedUser = UserTestUtils.generateUser(id, email, password);
        UserResponseDto expectedUser = UserResponseDto.builder()
                .id(id)
                .email(email)
                .role(UserRole.USER.name())
                .build();

        when(userMapper.mapToUser(userInput)).thenReturn(userToSave);
        when(userRepository.save(userToSave)).thenReturn(savedUser);
        when(userMapper.mapToUserResponseDto(savedUser)).thenReturn(expectedUser);

        UserResponseDto actualUser = userService.registerUser(userInput);

        assertEquals(id, actualUser.getId());
        assertEquals(email, actualUser.getEmail());
        assertEquals(UserRole.USER.name(), actualUser.getRole());
        verify(userRepository, times(1)).findByEmail(email);
        verify(userRepository, times(1)).save(userToSave);
        verify(userMapper, times(1)).mapToUser(userInput);
        verify(userMapper, times(1)).mapToUserResponseDto(savedUser);
    }

    @Test
    void shouldThrowException_whenEmailExisted() {
        String existedEmail = "existed_email@local.dev";
        String password = "$2a$10$cb.KYABzfcZSqTwDzvqsBe9cuE7sDH/F5TMOJVuyvann492vm6Xgm";
        String errorMessage = String.format(
                "Email: %s is existed, please check and use a different email to create user",
                existedEmail
        );

        UserDto userInput = UserTestUtils.generateUserDto(existedEmail, "password");
        User savedUser = UserTestUtils.generateUser(1L, existedEmail, password);

        when(userRepository.findByEmail(existedEmail)).thenReturn(Optional.of(savedUser));

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> userService.registerUser(userInput)
        );

        assertEquals(errorMessage, exception.getMessage());
        verify(userRepository, never()).save(any());
        verify(userMapper, never()).mapToUser(any());
        verify(userMapper, never()).mapToUserResponseDto(any());
    }
}
