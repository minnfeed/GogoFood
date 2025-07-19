package com.example.springbootservices.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRestaurantProfileRequest {
    private String name;
    private String description;
    private String imageUrl;
    private String address;
    private String openingHours;
}
