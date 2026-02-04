package com.nhannh.ecommerce.services.impl;

import com.nhannh.ecommerce.domain.dtos.ProductDto;
import com.nhannh.ecommerce.domain.entities.Product;
import com.nhannh.ecommerce.mappers.ProductMapper;
import com.nhannh.ecommerce.repositories.ProductRepository;
import com.nhannh.ecommerce.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::mapToDto)
                .toList();
    }

    @Override
    public ProductDto getProductById(Long productId) {
        return productRepository.findById(productId)
                .map(productMapper::mapToDto)
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("Cannot found product with id: %d", productId)
                ));
    }

    @Override
    public Product createProduct(ProductDto productDto) {
        return productRepository.save(productMapper.mapToEntity(productDto));
    }

    @Override
    public Product updateProduct(Long productId, ProductDto productDto) {
        Optional<Product> oldProduct = productRepository.findById(productId);
        if (oldProduct.isPresent()) {
            productDto.setId(productId);
            return productRepository.save(productMapper.mapToEntity(productDto));
        }
        throw new NoSuchElementException(String.format("Cannot found product with id: %d", productId));
    }

    @Override
    public void deleteProductById(Long productId) {
        productRepository.deleteById(productId);
    }
}
