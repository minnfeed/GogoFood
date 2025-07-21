package com.example.springbootservices.dto;

import com.example.springbootservices.model.entites.RestaurantProfile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantProfileResponse {

        private UUID id;
        private String name;
        private String description;
        private String imageUrl;
        private String address;
        private String openingHours;
        private Double latitude;
        private Double longitude;
        private Boolean isOpen;
        public RestaurantProfileResponse mapToDto(RestaurantProfile entity) {
                return new RestaurantProfileResponse(
                        entity.getId(),
                        entity.getName(),
                        entity.getDescription(),
                        entity.getImageUrl(),
                        entity.getAddress(),
                        entity.getOpeningHours(),
                        entity.getLatitude(),
                        entity.getLongitude(),
                        entity.getIsOpen()
                );
        }
}
