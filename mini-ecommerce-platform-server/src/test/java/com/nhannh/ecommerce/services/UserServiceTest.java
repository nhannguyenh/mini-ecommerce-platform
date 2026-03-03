package com.nhannh.ecommerce.services;

import com.nhannh.ecommerce.domain.UserRole;
import com.nhannh.ecommerce.domain.dtos.users.UserDto;
import com.nhannh.ecommerce.domain.dtos.users.UserResponseDto;
import com.nhannh.ecommerce.domain.entities.User;
import com.nhannh.ecommerce.mappers.UserMapper;
import com.nhannh.ecommerce.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        UserDto userInput = generateUserDto(email, password);
        User userToSave = generateUser(null, email, password);
        User savedUser = generateUser(id, email, password);
        UserResponseDto expectedUser = UserResponseDto.builder()
                .id(id)
                .email(email)
                .role(UserRole.USER)
                .build();

        when(userMapper.mapToUser(userInput)).thenReturn(userToSave);
        when(userRepository.save(userToSave)).thenReturn(savedUser);
        when(userMapper.mapToUserResponseDto(savedUser)).thenReturn(expectedUser);

        UserResponseDto actualUser = userService.registerUser(userInput);

        assertEquals(id, actualUser.getId());
        assertEquals(email, actualUser.getEmail());
        assertEquals(UserRole.USER, actualUser.getRole());
        verify(userRepository, times(1)).findByEmail(email);
        verify(userRepository, times(1)).save(userToSave);
        verify(userMapper, times(1)).mapToUser(userInput);
        verify(userMapper, times(1)).mapToUserResponseDto(savedUser);
    }

    private UserDto generateUserDto(String email, String password) {
        return UserDto.builder()
                .email(email)
                .password(password)
                .role(UserRole.USER)
                .build();
    }

    private User generateUser(Long id, String email, String password) {
        return User.builder()
                .id(id)
                .email(email)
                .password(password)
                .role(UserRole.USER)
                .build();
    }
}
