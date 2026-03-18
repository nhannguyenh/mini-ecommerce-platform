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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

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

        List<CartItemDto> items = cartDto.getItems();
        Set<Long> productIds = items.stream()
                .map((item) -> item.getProduct().getId())
                .collect(Collectors.toSet());

        double totalPrice = cartDto.getTotalPrice();
        double productPrice = productDto.getPrice();
        if (items.isEmpty() || !productIds.contains(itemRequestDto.getProductId())) {
            CartItemDto cartItemDto = CartItemDto.builder()
                    .cartId(cartDto.getId())
                    .product(productDto)
                    .quantity(itemRequestDto.getQuantity())
                    .price(productPrice)
                    .build();
            CartItemDto newItem = cartItemService.addOrUpdateCartItem(cartItemDto);
            totalPrice += newItem.getPrice() * newItem.getQuantity();

            items.add(newItem);
            cartDto.setItems(items);
            cartDto.setTotalPrice(totalPrice);
        } else {
            for (CartItemDto item : items) {
                double oldPrice = 0;
                if (itemRequestDto.getProductId().equals(item.getProduct().getId())) {
                    oldPrice = item.getQuantity() * item.getPrice();
                    item.setQuantity(itemRequestDto.getQuantity());
                    if (productPrice != item.getPrice()) {
                        item.setPrice(productPrice);
                    }
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
        List<CartItemDto> items = cartDto.getItems();
        Set<Long> itemIds = getItemIds(items);

        if (itemIds.contains(itemId)) {
            CartItemDto cartItem = items.stream()
                    .filter(item -> itemId.equals(item.getId()))
                    .findFirst()
                    .orElse(null);
            if (Objects.nonNull(cartItem)) {
                totalPrice -= cartItem.getPrice() * cartItem.getQuantity();
                itemIds.remove(itemId);
                items.remove(cartItem);
                cartItemService.removeItem(itemId);

                cartDto.setTotalPrice(totalPrice);
                cartDto.setItems(items);
                cartRepository.save(cartMapper.mapToEntity(cartDto));
            }
        } else {
            throw new NoSuchElementException(String.format("Item has id %d cannot be found in the current cart", itemId));
        }
        return cartDto;
    }

    public CartDto clearCart(Long userId) {
        CartDto cartDto = getCart(userId);
        Set<Long> itemIds = getItemIds(cartDto.getItems());

        cartItemService.removeAllItems(itemIds);
        itemIds.clear();

        cartDto.setItems(new ArrayList<>());
        cartDto.setTotalPrice(0.0);
        cartRepository.save(cartMapper.mapToEntity(cartDto));
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

    private Set<Long> getItemIds(List<CartItemDto> items) {
        return items.stream()
                .map(CartItemDto::getId)
                .collect(Collectors.toSet());
    }
}
