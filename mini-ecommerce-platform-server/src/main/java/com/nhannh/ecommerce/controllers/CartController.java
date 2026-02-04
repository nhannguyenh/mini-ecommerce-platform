package com.nhannh.ecommerce.controllers;

import com.nhannh.ecommerce.domain.EcommerceUserDetails;
import com.nhannh.ecommerce.domain.dtos.AddCartItemRequestDto;
import com.nhannh.ecommerce.domain.dtos.CartDto;
import com.nhannh.ecommerce.domain.dtos.CartResponseDto;
import com.nhannh.ecommerce.domain.entities.CartItem;
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
    public ResponseEntity<CartResponseDto> addItem(@AuthenticationPrincipal EcommerceUserDetails userDetails,
                                                   @RequestBody AddCartItemRequestDto addCartItemRequestDto) {
        CartDto cartDto = cartService.addItems(userDetails.getUserId(), addCartItemRequestDto);
        return new ResponseEntity<>(
                CartResponseDto.builder()
                        .id(cartDto.getId())
                        .userId(cartDto.getUserId())
                        .status(cartDto.getStatus())
                        .totalPrice(cartDto.getTotalPrice())
                        .items(cartDto.getItems().stream()
                                .map(CartItem::getId)
                                .toList()
                        )
                        .createdOn(cartDto.getCreatedOn())
                        .modifiedOn(cartDto.getModifiedOn())
                        .build(),
                HttpStatus.CREATED
        );
    }
}
