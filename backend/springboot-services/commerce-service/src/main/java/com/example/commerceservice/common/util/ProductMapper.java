package com.example.commerceservice.common.util;

import com.example.commerceservice.domain.model.entity.Product;
import com.example.commerceservice.web.dto.ProductDTO;
import com.example.commerceservice.application.usecase.product.CreateProductCommand;

import java.time.LocalDateTime;
import java.util.UUID;

public class ProductMapper {

    // Convert command → entity
    public static Product toEntity(CreateProductCommand command) {
        return Product.builder()
                .id(UUID.randomUUID())
                .name(command.getName())
                .description(command.getDescription())
                .price(command.getPrice())
                .status(command.getStatus())
                .categoryId(command.getCategoryId())
                .storeId(command.getStoreId())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    // Convert entity → DTO
    public static ProductDTO toDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .status(product.getStatus())
                .categoryId(product.getCategoryId())
                .storeId(product.getStoreId())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }
}