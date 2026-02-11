package com.nhannh.ecommerce.services;

import com.nhannh.ecommerce.domain.OrderStatus;
import com.nhannh.ecommerce.domain.dtos.*;
import com.nhannh.ecommerce.domain.entities.Order;
import com.nhannh.ecommerce.mappers.OrderItemMapper;
import com.nhannh.ecommerce.mappers.OrderMapper;
import com.nhannh.ecommerce.repositories.OrderItemRepository;
import com.nhannh.ecommerce.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CartService cartService;
    private final CartItemService cartItemService;
    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;

    @Transactional
    public OrderDto createOrderFromCart(Long userId) {
        // get cart from current user
        CartDto cartDto = cartService.getCart(userId);
        if (Objects.isNull(cartDto)) {
            throw new NoSuchElementException("The current user doesn't have any active cart");
        }
        if (Objects.isNull(cartDto.getItems()) || cartDto.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        // get cart items
        List<CartItemDto> cartItems = cartItemService.findByCartId(cartDto.getId());

        // get products from items
        Set<Long> productIds = cartItems.stream()
                .map(CartItemDto::getProductId)
                .collect(Collectors.toSet());
        List<ProductDto> productDtos = productService.getProductsByIds(productIds);
        Map<Long, ProductDto> mapProductById = productDtos.stream()
                .collect(Collectors.toMap(ProductDto::getId, product -> product));

        // create order
        Order order = Order.builder()
                .userId(cartDto.getUserId())
                .status(OrderStatus.CREATED)
                .totalPrice(cartDto.getTotalPrice())
                .build();
        order = orderRepository.save(order);

        // convert cart items to order items
        for (CartItemDto cartItem : cartItems) {
            ProductDto productDto = mapProductById.get(cartItem.getProductId());
            double subTotal = productDto.getPrice() * cartItem.getQuantity();

            OrderItemDto orderItem = OrderItemDto.builder()
                    .orderId(order.getId())
                    .productId(productDto.getId())
                    .productName(productDto.getName())
                    .productPrice(productDto.getPrice())
                    .quantity(cartItem.getQuantity())
                    .subTotal(subTotal)
                    .build();
            orderItemRepository.save(orderItemMapper.mapToEntity(orderItem));
        }
        return orderMapper.mapToDto(order);
    }
}
