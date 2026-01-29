package com.nhannh.ecommerce.domain.entities;

import com.nhannh.ecommerce.domain.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(nullable = false)
    private LocalDateTime createdOn;

    @Column(nullable = false)
    private LocalDateTime modifiedOn;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(email, user.email)
                && Objects.equals(password, user.password)
                && role == user.role
                && Objects.equals(createdOn, user.createdOn)
                && Objects.equals(modifiedOn, user.modifiedOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, role, createdOn, modifiedOn);
    }

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdOn = now;
        this.modifiedOn = now;
    }

    @PreUpdate
    protected void onModify() {
        this.modifiedOn = LocalDateTime.now();
    }
}
