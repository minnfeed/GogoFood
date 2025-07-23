package com.example.commerceservice.model.dto;

import lombok.Data;

@Data
public class ProductImageRequest {
    private String imageUrl;
    private String isMain; // "true" hoặc "false"

    public boolean isMainBoolean() {
        return Boolean.parseBoolean(isMain);
    }
}