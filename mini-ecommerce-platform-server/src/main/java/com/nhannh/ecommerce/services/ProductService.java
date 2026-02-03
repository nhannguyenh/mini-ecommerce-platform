package com.nhannh.ecommerce.services;

import com.nhannh.ecommerce.domain.dtos.ProductDto;
import com.nhannh.ecommerce.domain.entities.Product;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();
    ProductDto getProductById(Long productId);
    Product createProduct(ProductDto productDto);
    Product updateProduct(Long productId, ProductDto productDto);
    void deleteProductById(Long productId);
}
