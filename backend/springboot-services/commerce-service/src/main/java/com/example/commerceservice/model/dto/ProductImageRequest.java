package com.example.commerceservice.model.dto;

import lombok.Data;

@Data
public class ProductImageRequest {
    private String imageUrl;
    private boolean isMain;

}