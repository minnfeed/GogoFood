package com.example.commerceservice.service;

import com.example.commerceservice.model.dto.CreateProductRequest;
import com.example.commerceservice.model.projection.ProductPreviewProjection;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<ProductPreviewProjection> getProductPreviewsByCategory(UUID categoryId);
    List<ProductPreviewProjection> getProductPreviewsByKeyword(String keyword);
    void createProduct(CreateProductRequest product);
}
