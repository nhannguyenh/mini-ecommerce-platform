package com.nhannh.ecommerce.mappers;

import com.nhannh.ecommerce.domain.dtos.ProductDto;
import com.nhannh.ecommerce.domain.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product mapToEntity(ProductDto productDto) {
        return Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .sku(productDto.getSku())
                .stockQuantity(productDto.getStockQuantity())
                .createdOn(productDto.getCreatedOn())
                .modifiedOn(productDto.getModifiedOn())
                .build();

    }

    public ProductDto mapToDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .sku(product.getSku())
                .stockQuantity(product.getStockQuantity())
                .createdOn(product.getCreatedOn())
                .modifiedOn(product.getModifiedOn())
                .build();
    }
}
