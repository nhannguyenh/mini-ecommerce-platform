package com.nhannh.ecommerce.services;

import com.nhannh.ecommerce.domain.CartStatus;
import com.nhannh.ecommerce.domain.dtos.CartDto;
import com.nhannh.ecommerce.domain.dtos.CartItemDto;
import com.nhannh.ecommerce.domain.dtos.CartItemRequestDto;
import com.nhannh.ecommerce.domain.dtos.ProductDto;
import com.nhannh.ecommerce.domain.entities.Cart;
import com.nhannh.ecommerce.mappers.CartMapper;
import com.nhannh.ecommerce.repositories.CartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {
    private final CartMapper cartMapper;
    private final CartRepository cartRepository;

    private final ProductService productService;
    private final CartItemService cartItemService;

    @Transactional
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

    @Transactional
    public CartDto addOrUpdateItems(Long userId, CartItemRequestDto itemRequestDto) {
        CartDto cartDto = getCart(userId);
        ProductDto productDto = productService.getProductById(itemRequestDto.getProductId());
        this.validate(productDto, itemRequestDto);

        List<CartItemDto> items = cartItemService.findByCartId(cartDto.getId());
        Set<Long> productIds = items.stream()
                .map(CartItemDto::getProductId)
                .collect(Collectors.toSet());

        double totalPrice = cartDto.getTotalPrice();
        if (items.isEmpty() || !productIds.contains(itemRequestDto.getProductId())) {
            CartItemDto cartItemDto = CartItemDto.builder()
                    .cartId(cartDto.getId())
                    .productId(itemRequestDto.getProductId())
                    .quantity(itemRequestDto.getQuantity())
                    .price(productDto.getPrice())
                    .build();
            CartItemDto newItem = cartItemService.addOrUpdateCartItem(cartItemDto);
            totalPrice += newItem.getPrice() * newItem.getQuantity();

            cartDto.getItems().add(newItem.getId());
            cartDto.setTotalPrice(totalPrice);
        } else {
            for (CartItemDto item : items) {
                double oldPrice = 0;
                if (itemRequestDto.getProductId().equals(item.getProductId())) {
                    oldPrice = item.getQuantity() * item.getPrice();
                    item.setQuantity(itemRequestDto.getQuantity());
                    cartItemService.addOrUpdateCartItem(item);
                    totalPrice += item.getQuantity() * item.getPrice() - oldPrice;
                }
            }
            cartDto.setTotalPrice(totalPrice);
        }

        cartRepository.save(cartMapper.mapToEntity(cartDto));
        return cartDto;
    }

    @Transactional
    public CartDto removeItem(Long userId, Long itemId) {
        CartDto cartDto = this.getCart(userId);

        double totalPrice = cartDto.getTotalPrice();
        Set<Long> itemIds = cartDto.getItems();
        if (itemIds.contains(itemId)) {
            CartItemDto cartItemDto = cartItemService.findById(itemId);
            totalPrice -= cartItemDto.getPrice() * cartItemDto.getQuantity();
            itemIds.remove(itemId);
            cartItemService.removeItem(itemId);

            cartDto.setTotalPrice(totalPrice);
            cartDto.setItems(itemIds);
            cartRepository.save(cartMapper.mapToEntity(cartDto));
        } else {
            log.warn("Item {} doesn't exist in the cart", itemId);
        }
        return cartDto;
    }

    private void validate(ProductDto productDto, CartItemRequestDto itemRequestDto) {
        if (itemRequestDto.getQuantity() < 1) {
            throw new IllegalArgumentException("Product quantity must be greater than or equal to 1");
        }

        if (itemRequestDto.getQuantity() > productDto.getStockQuantity()) {
            throw new IllegalArgumentException("Product quantity is exceeding the available stock quantity");
        }
    }
}
