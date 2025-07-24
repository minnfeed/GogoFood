package com.example.springbootservices.controller;

import com.example.springbootservices.dto.OpenStreetMapResponse;
import com.example.springbootservices.service.GeocodingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/geocoding")
@RequiredArgsConstructor
@Tag(name = "Geocoding API", description = "API tìm kiếm địa chỉ thông qua OpenStreetMap")
public class GeocodingController {

    private final GeocodingService geocodingService;

    @Operation(
            summary = "Tìm kiếm địa chỉ",
            description = """
            Dùng để tìm kiếm địa chỉ từ chuỗi văn bản thông qua OpenStreetMap API.
            Trả về danh sách kết quả bao gồm thông tin tọa độ (lat/lon), tên địa điểm và địa chỉ đầy đủ.
        """
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lấy danh sách địa chỉ thành công"),
            @ApiResponse(responseCode = "400", description = "Query không hợp lệ"),
            @ApiResponse(responseCode = "500", description = "Lỗi hệ thống")
    })
    @GetMapping("/search")
    public ResponseEntity<List<OpenStreetMapResponse>> searchAddress(@RequestParam String query) {
        List<OpenStreetMapResponse> results = geocodingService.searchAddress(query);
        return ResponseEntity.ok(results);
    }
}