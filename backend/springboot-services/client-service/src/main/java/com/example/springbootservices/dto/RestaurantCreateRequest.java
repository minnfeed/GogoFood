package com.example.springbootservices.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantCreateRequest {

    private String name;
    private String description;
    private String imageUrl;
    private String address;
    private String openingHours;
    private Double latitude;
    private Double longitude;

}