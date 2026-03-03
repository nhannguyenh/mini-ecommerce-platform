package com.nhannh.ecommerce.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhannh.ecommerce.domain.UserRole;
import com.nhannh.ecommerce.domain.dtos.users.UserDto;
import com.nhannh.ecommerce.domain.dtos.users.UserResponseDto;
import com.nhannh.ecommerce.filters.JwtAuthenticationFilter;
import com.nhannh.ecommerce.services.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockitoBean
    private UserService userService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        objectMapper = new ObjectMapper();
    }

    @AfterEach
    void tearDown() {
        objectMapper = null;
    }

    @Test
    void shouldCreateUser_whenRequestValid_thenReturnOk() throws Exception {
        String email = "test@local.com";
        UserDto requestUser = UserDto.builder()
                .email(email)
                .password("password")
                .role(UserRole.USER)
                .build();

        UserResponseDto mockUserResponse = UserResponseDto.builder()
                .id(1L)
                .email(email)
                .role(UserRole.USER)
                .build();

        when(userService.registerUser(requestUser)).thenReturn(mockUserResponse);

        mockMvc.perform(post("/api/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestUser))
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value(email));
        verify(userService, times(1)).registerUser(requestUser);
    }
}