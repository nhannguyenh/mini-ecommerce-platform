package com.nhannh.ecommerce.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class AbstractEntity {

    @Column(nullable = false)
    protected LocalDateTime createdOn;

    @Column(nullable = false)
    protected LocalDateTime modifiedOn;

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
