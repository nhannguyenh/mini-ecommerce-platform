package com.nhannh.ecommerce.controllers;

import com.nhannh.ecommerce.domain.EcommerceUserDetails;
import com.nhannh.ecommerce.domain.dtos.CartDto;
import com.nhannh.ecommerce.domain.dtos.CartItemRequestDto;
import com.nhannh.ecommerce.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    public ResponseEntity<CartDto> getCart(@AuthenticationPrincipal EcommerceUserDetails userDetails) {
        return ResponseEntity.ok(cartService.getCart(userDetails.getUserId()));
    }

    @PostMapping("/items")
    public ResponseEntity<CartDto> addItem(@AuthenticationPrincipal EcommerceUserDetails userDetails,
                                                   @RequestBody CartItemRequestDto addCartItemRequestDto) {
        return new ResponseEntity<>(
                cartService.addItems(userDetails.getUserId(), addCartItemRequestDto),
                HttpStatus.CREATED
        );
    }
}
