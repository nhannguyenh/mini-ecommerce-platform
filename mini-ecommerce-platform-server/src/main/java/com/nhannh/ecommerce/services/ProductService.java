package com.nhannh.ecommerce.services;

import com.nhannh.ecommerce.domain.dtos.ProductDto;
import com.nhannh.ecommerce.domain.entities.Product;
import com.nhannh.ecommerce.mappers.ProductMapper;
import com.nhannh.ecommerce.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    @Transactional
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::mapToDto)
                .toList();
    }

    @Transactional
    public ProductDto getProductById(Long productId) {
        return productRepository.findById(productId)
                .map(productMapper::mapToDto)
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("Cannot found product with id: %d", productId)
                ));
    }

    @Transactional
    public List<ProductDto> getProductsByIds(Set<Long> productIds) {
        return productRepository.findAllById(productIds).stream()
                .map(productMapper::mapToDto)
                .toList();
    }

    @Transactional
    public Product createProduct(ProductDto productDto) {
        return productRepository.save(productMapper.mapToEntity(productDto));
    }

    @Transactional
    public Product updateProduct(Long productId, ProductDto productDto) {
        Optional<Product> oldProduct = productRepository.findById(productId);
        if (oldProduct.isPresent()) {
            productDto.setId(productId);
            return productRepository.save(productMapper.mapToEntity(productDto));
        }
        throw new NoSuchElementException(String.format("Cannot found product with id: %d", productId));
    }

    @Transactional
    public void deleteProductById(Long productId) {
        productRepository.deleteById(productId);
    }
}
