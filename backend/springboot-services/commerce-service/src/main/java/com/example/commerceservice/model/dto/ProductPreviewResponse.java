package com.example.commerceservice.model.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class ProductPreviewResponse {
    private UUID id;
    private String name;
    private BigDecimal price;
    private String thumbnailUrl; // ảnh đầu tiên
}
