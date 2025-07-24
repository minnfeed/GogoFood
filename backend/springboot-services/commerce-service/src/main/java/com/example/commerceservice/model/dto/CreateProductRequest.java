package com.example.commerceservice.model.dto;

import com.example.commerceservice.model.valueobject.Status;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
public class CreateProductRequest {

    private String name;

    private String description;

    private BigDecimal price;

    private Status status;

    private UUID categoryId;


    private List<ProductImageDto> images;

}