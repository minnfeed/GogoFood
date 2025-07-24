package com.example.springbootservices.dto;

import lombok.Data;

@Data
public class UpdateLocationRequest {
    private Double latitude;
    private Double longitude;
}