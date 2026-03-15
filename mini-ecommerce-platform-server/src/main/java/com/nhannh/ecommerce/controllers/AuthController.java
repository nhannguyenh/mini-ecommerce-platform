package com.nhannh.ecommerce.controllers;

import com.nhannh.ecommerce.domain.EcommerceUserDetails;
import com.nhannh.ecommerce.domain.dtos.AuthResponseDto;
import com.nhannh.ecommerce.domain.dtos.LoginRequestDto;
import com.nhannh.ecommerce.domain.dtos.users.UserResponseDto;
import com.nhannh.ecommerce.services.AuthenticationService;
import com.nhannh.ecommerce.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
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

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        EcommerceUserDetails userDetails = authenticationService.authenticate(
                loginRequestDto.getEmail(),
                loginRequestDto.getPassword()
        );
        return ResponseEntity.ok(
                AuthResponseDto.builder()
                        .token(jwtUtils.generateToken(userDetails))
                        .user(
                                UserResponseDto.builder()
                                        .id(userDetails.getUserId())
                                        .email(userDetails.getUsername())
                                        .role(
                                                userDetails.getAuthorities().stream()
                                                        .findFirst()
                                                        .map(GrantedAuthority::getAuthority)
                                                        .orElse(null)
                                        )
                                        .build()
                        )
                        .build()
        );
    }
}
