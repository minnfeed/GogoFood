package com.example.commerceservice.web.dto;

import com.example.commerceservice.domain.model.entity.Product;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductDTO {
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;

}