package com.example.commerceservice.model.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class RestaurantInfoResponse {
    private UUID id;
    private String name;
    private Boolean isOpen;
    // ... các field cần thiết
}
