package com.nhannh.ecommerce.controllers;

import com.nhannh.ecommerce.domain.dtos.AuthResponseDto;
import com.nhannh.ecommerce.domain.dtos.LoginRequestDto;
import com.nhannh.ecommerce.services.AuthenticationService;
import com.nhannh.ecommerce.services.UserService;
import com.nhannh.ecommerce.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;
    private final JwtUtils jwtUtils;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        UserDetails userDetails = authenticationService.authenticate(
                loginRequestDto.getEmail(),
                loginRequestDto.getPassword()
        );
        return ResponseEntity.ok(
                AuthResponseDto.builder()
                        .token(jwtUtils.generateToken(userDetails))
                        .tokenLifespanMillis(jwtUtils.getJwtTokenLifeSpan())
                        .build()
        );
    }
}
