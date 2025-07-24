package com.example.springbootservices.controller;

import com.example.springbootservices.service.RestaurantProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/internal/restaurant-profile")
@RequiredArgsConstructor
public class InternalRestaurantProfileController {

    private final RestaurantProfileService restaurantProfileService;

    @GetMapping("/id/by-user/{userId}")
    public ResponseEntity<UUID> getRestaurantIdByUserId(@PathVariable UUID userId) {
        UUID restaurantId = restaurantProfileService.getByUserId(userId).getId();
        return ResponseEntity.ok(restaurantId);
    }
}