package com.nhannh.ecommerce.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Objects;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_items")
public class OrderItem extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long orderId;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private Double productPrice;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double subTotal;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(id, orderItem.id)
                && Objects.equals(orderId, orderItem.orderId)
                && Objects.equals(productId, orderItem.productId)
                && Objects.equals(productName, orderItem.productName)
                && Objects.equals(productPrice, orderItem.productPrice)
                && Objects.equals(quantity, orderItem.quantity)
                && Objects.equals(subTotal, orderItem.subTotal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, orderId, productId, productName, productPrice, quantity, subTotal);
    }
}
