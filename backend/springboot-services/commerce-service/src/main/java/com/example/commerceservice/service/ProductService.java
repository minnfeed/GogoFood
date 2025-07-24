package com.example.commerceservice.service;

import com.example.commerceservice.model.dto.CreateProductRequest;
import com.example.commerceservice.model.dto.ProductDetailDto;
import com.example.commerceservice.model.projection.ProductPreviewProjection;
import com.example.commerceservice.model.valueobject.Status;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<ProductPreviewProjection> getProductPreviewsByCategory(UUID categoryId);
    List<ProductPreviewProjection> getProductPreviewsByKeyword(String keyword);
    void createProduct(CreateProductRequest product);
    void updateStatus(UUID id, Status status);

    ProductDetailDto getProductDetail(UUID id);
}
