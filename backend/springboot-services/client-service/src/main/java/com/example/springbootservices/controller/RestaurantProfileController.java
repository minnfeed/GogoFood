package com.example.springbootservices.controller;

import com.example.springbootservices.dto.CreateRestaurantProfileRequest;
import com.example.springbootservices.dto.UpdateRestaurantProfileRequest;
import com.example.springbootservices.service.RestaurantProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.UUID;

@RestController
@RequestMapping("/restaurant/profile")
@RequiredArgsConstructor
public class RestaurantProfileController {

    @Autowired
    private final RestaurantProfileService restaurantProfileService;

    @PutMapping("/{id}")
    public ResponseEntity<String> updateRestaurantProfile(
            @PathVariable UUID id,
            @RequestBody UpdateRestaurantProfileRequest request
    ) {
        restaurantProfileService.update(id, request);
        return ResponseEntity.ok("Cập nhật thông tin nhà hàng thành công!");
    }
    @PostMapping
    public ResponseEntity<?> createRestaurantProfile(@RequestBody CreateRestaurantProfileRequest request) throws AccessDeniedException {
        restaurantProfileService.create(request);
        return ResponseEntity.ok("Tạo nhà hàng thành công");
    }
}
