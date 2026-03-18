package com.nhannh.ecommerce.services;

import com.nhannh.ecommerce.domain.dtos.CartItemDto;
import com.nhannh.ecommerce.domain.dtos.ProductDto;
import com.nhannh.ecommerce.domain.entities.CartItem;
import com.nhannh.ecommerce.mappers.CartItemMapper;
import com.nhannh.ecommerce.repositories.CartItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartItemService {
    private final CartItemMapper cartItemMapper;
    private final CartItemRepository cartItemRepository;
    private final ProductService productService;

    @Transactional
    public CartItemDto findById(Long id) {
        Optional<CartItem> cartItemOnDb = cartItemRepository.findById(id);
        if (cartItemOnDb.isPresent()) {
            CartItem cartItem = cartItemOnDb.get();

            try {
                ProductDto product = productService.getProductById(cartItem.getProductId());
                return cartItemMapper.mapToDto(cartItem, product);
            } catch (NoSuchElementException e) {
                log.error("Cannot found product with id: {}", cartItem.getProductId());
            }
        } else {
            log.error("Item cannot be found with id: {}", id);
        }
        return null;
    }

    @Transactional
    public List<CartItemDto> findByCartId(Long cartId) {
        List<CartItem> cartItems = cartItemRepository.findByCartId(cartId);

        Map<Long, ProductDto> productMap = mapProductById(cartItems);

        return cartItemRepository.findByCartId(cartId).stream()
                .map((item) -> cartItemMapper.mapToDto(item, productMap.get(item.getProductId())))
                .collect(Collectors.toList());
    }

    @Transactional
    public CartItemDto addOrUpdateCartItem(CartItemDto cartItemDto) {
        CartItem cartItem = cartItemRepository.save(cartItemMapper.mapToEntity(cartItemDto));
        try {
            ProductDto product = productService.getProductById(cartItem.getProductId());
            return cartItemMapper.mapToDto(cartItem, product);
        } catch (NoSuchElementException e) {
            log.error("Cannot found product with id: {}", cartItem.getProductId());
        }
        return null;
    }

    @Transactional
    public void removeItem(Long itemId) {
        cartItemRepository.deleteById(itemId);
    }

    @Transactional
    public void removeAllItems(Set<Long> itemIds) {
        cartItemRepository.deleteAllById(itemIds);
    }

    private Map<Long, ProductDto> mapProductById(List<CartItem> cartItems) {
        Set<Long> productIds = cartItems.stream()
                .map(CartItem::getProductId)
                .collect(Collectors.toSet());
        List<ProductDto> products = productService.getProductsByIds(productIds);

        return products.stream()
                .collect(
                        Collectors.toMap(
                                ProductDto::getId,
                                productDto -> productDto
                        )
                );
    }
}
