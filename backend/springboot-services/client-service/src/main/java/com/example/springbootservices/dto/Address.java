package com.example.springbootservices.dto;

import lombok.*;

import java.util.UUID;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private UUID id;
    private String street;
    private String ward;
    private String district;
    private String city;
    private boolean isDefault;
}
