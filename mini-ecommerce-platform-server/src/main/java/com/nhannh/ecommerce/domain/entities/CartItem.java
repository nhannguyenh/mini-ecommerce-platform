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
@Table(name = "cart_items")
public class CartItem extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long cartId;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double price;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CartItem cartItem = (CartItem) o;
        return Objects.equals(id, cartItem.id)
                && Objects.equals(cartId, cartItem.cartId)
                && Objects.equals(productId, cartItem.productId)
                && Objects.equals(quantity, cartItem.quantity)
                && Objects.equals(price, cartItem.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, cartId, productId, quantity, price);
    }
}
