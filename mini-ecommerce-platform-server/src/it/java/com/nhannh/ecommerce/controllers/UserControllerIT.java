package com.nhannh.ecommerce.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhannh.ecommerce.AbstractIntegrationTest;
import com.nhannh.ecommerce.domain.UserRole;
import com.nhannh.ecommerce.domain.dtos.users.UserDto;
import com.nhannh.ecommerce.domain.entities.User;
import com.nhannh.ecommerce.repositories.UserRepository;
import com.nhannh.ecommerce.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserControllerIT extends AbstractIntegrationTest {
    private final String REGISTER_API_URL = "/api/users/register";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldCreateUser_whenRequestValid_thenReturnOk() throws Exception {
        String email = "test@local.dev";
        UserDto requestUser = this.createRequestUser(email);

        mockMvc.perform(post(REGISTER_API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestUser))
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value(email));

        Optional<User> user = userRepository.findByEmail(email);
        assertTrue(user.isPresent());
        assertEquals(1, user.get().getId());
        assertEquals(email, user.get().getEmail());
        assertEquals(UserRole.USER, user.get().getRole());
    }

    @Test
    void shouldReturn400_whenEmailExisted() throws Exception {
        String email = "existed@local.dev";
        String errorMessage = String.format(
                "Email: %s is existed, please check and use a different email to create user",
                email
        );
        UserDto requestUser = this.createRequestUser(email);

        userRepository.save(
                User.builder()
                        .email(email)
                        .password("$2a$10$cb.KYABzfcZSqTwDzvqsBe9cuE7sDH/F5TMOJVuyvann492vm6Xgm")
                        .role(UserRole.USER)
                        .build()
        );

        mockMvc.perform(post(REGISTER_API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestUser))
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.statusCode").value(400))
                .andExpect(jsonPath("$.message").value(errorMessage));
    }

    private UserDto createRequestUser(String email) {
        return UserDto.builder()
                .email(email)
                .password("password")
                .role(UserRole.USER)
                .build();
    }
}
