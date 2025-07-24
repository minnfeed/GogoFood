package com.example.commerceservice.model.dto;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class ProductDetailDto {
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private String status;
    private UUID categoryId;
    private String categoryName;

    private UUID storeId;
    private String storeName;

    private List<ProductImageDto> images;
    private double averageRating;
    private int totalReviews;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
