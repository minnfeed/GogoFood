package com.example.commerceservice.ClientService;

import com.example.commerceservice.model.dto.RestaurantInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "client-service")
public interface ClientServiceClient {

    @GetMapping("/internal/restaurant-profile/by-user/{userId}")
    RestaurantInfoResponse getRestaurantByUserId(@PathVariable("userId") UUID userId);
}