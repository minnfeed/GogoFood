package com.example.commerceservice.model.dto;

import com.example.commerceservice.model.valueobject.Status;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class CreateProductRequest {

    private String name;

    private String description;

    private BigDecimal price;

    private Status status;

    private UUID categoryId;

    private UUID storeId;

    private List<ProductImageRequest> images;

}