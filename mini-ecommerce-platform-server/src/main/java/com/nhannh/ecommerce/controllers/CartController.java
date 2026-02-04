package com.nhannh.ecommerce.controllers;

import com.nhannh.ecommerce.domain.EcommerceUserDetails;
import com.nhannh.ecommerce.domain.dtos.AddCartItemRequestDto;
import com.nhannh.ecommerce.domain.dtos.CardResponseDto;
import com.nhannh.ecommerce.domain.dtos.CartDto;
import com.nhannh.ecommerce.domain.entities.Cart;
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

    @PostMapping("/{userId}/items")
    public ResponseEntity<CardResponseDto> addItem(@PathVariable Long userId,
                                                   @RequestBody AddCartItemRequestDto addCartItemRequestDto) {
        Cart cart = cartService.addItems(userId, addCartItemRequestDto);
        CardResponseDto cardResponseDto = CardResponseDto.builder()
                .id(cart.getId())
                .userId(cart.getUserId())
                .status(cart.getStatus())
                .totalPrice(cart.getTotalPrice())
                .items(cart.getItems().stream()
                        .map(CartItem::getId)
                        .toList()
                )
                .build();
        return new ResponseEntity<>(cardResponseDto, HttpStatus.CREATED);
    }
}
