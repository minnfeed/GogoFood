package com.example.commerceservice.service.impl;

import com.example.commerceservice.model.projection.ProductPreviewProjection;
import com.example.commerceservice.repository.ProductRepository;
import com.example.commerceservice.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductPreviewProjection> getProductPreviewsByCategory(UUID categoryId) {
        return productRepository.findProductPreviewsByCategory(categoryId);
    }
    @Override
    public List<ProductPreviewProjection> getProductPreviewsByKeyword(String keyword) {
        return productRepository.searchProductPreviewsByKeyword(keyword);
    }
}
