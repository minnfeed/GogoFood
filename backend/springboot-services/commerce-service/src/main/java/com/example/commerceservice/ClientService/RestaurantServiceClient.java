package com.example.commerceservice.ClientService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "restaurant-service")
public interface RestaurantServiceClient {

    @GetMapping("/internal/restaurant-profile/id/by-user/{userId}")
    UUID getRestaurantIdByUserId(@PathVariable("userId") UUID userId);
}