package com.nhannh.ecommerce.services.impl;

import com.nhannh.ecommerce.domain.CartStatus;
import com.nhannh.ecommerce.domain.dtos.AddCartItemRequestDto;
import com.nhannh.ecommerce.domain.dtos.CartDto;
import com.nhannh.ecommerce.domain.dtos.CartItemDto;
import com.nhannh.ecommerce.domain.dtos.ProductDto;
import com.nhannh.ecommerce.domain.entities.Cart;
import com.nhannh.ecommerce.mappers.CartMapper;
import com.nhannh.ecommerce.repositories.CartRepository;
import com.nhannh.ecommerce.services.CartItemService;
import com.nhannh.ecommerce.services.CartService;
import com.nhannh.ecommerce.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartMapper cartMapper;
    private final CartRepository cartRepository;

    private final ProductService productService;
    private final CartItemService cartItemService;

    @Override
    public CartDto getCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> cartRepository.save(
                        Cart.builder()
                                .userId(userId)
                                .status(CartStatus.ACTIVE)
                                .totalPrice(0.0)
                                .build()
                ));
        List<CartItemDto> items = cartItemService.findByCartId(cart.getId());
        return cartMapper.mapToDto(cart, items);
    }

    @Override
    @Transactional
    public CartDto addItems(Long userId, AddCartItemRequestDto itemRequestDto) {
        // check cart is existed or not
        CartDto cartDto = getCart(userId);

        // check product is existed or not
        ProductDto productDto = productService.getProductById(itemRequestDto.getProductId());

        // check and update items
        List<CartItemDto> items = cartItemService.findByCartId(cartDto.getId());
        Set<Long> productIds = items.stream()
                .map(CartItemDto::getProductId)
                .collect(Collectors.toSet());

        double totalPrice = cartDto.getTotalPrice();
        if (items.isEmpty() || !productIds.contains(itemRequestDto.getProductId())) {
            // Create item
            CartItemDto cartItemDto = CartItemDto.builder()
                    .cartId(cartDto.getId())
                    .productId(itemRequestDto.getProductId())
                    .quantity(itemRequestDto.getQuantity())
                    .price(productDto.getPrice())
                    .build();
            CartItemDto newItem = cartItemService.addCartItem(cartItemDto);
            totalPrice += newItem.getPrice() * newItem.getQuantity();

            cartDto.getItems().add(newItem.getId());
            cartDto.setTotalPrice(totalPrice);
        } else {
            // calculate total price and update quantity
            for (CartItemDto item : items) {
                double oldPrice = 0;
                if (itemRequestDto.getProductId().equals(item.getProductId())) {
                    oldPrice = item.getQuantity() * item.getPrice();
                    item.setQuantity(itemRequestDto.getQuantity());
                    cartItemService.addCartItem(item);
                }
                totalPrice += item.getQuantity() * item.getPrice() - oldPrice;
            }
            cartDto.setTotalPrice(totalPrice);
        }

        // update cart
        cartRepository.save(cartMapper.mapToEntity(cartDto));
        return cartDto;
    }
}
