package com.example.springbootservices.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpenStreetMapResponse {
    private String lat;
    private String lon;

    @JsonProperty("display_name")
    private String displayName;

    // Getters
    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }

    public String getDisplayName() {
        return displayName;
    }
}
