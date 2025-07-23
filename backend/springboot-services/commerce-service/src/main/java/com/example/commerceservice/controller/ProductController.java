package com.example.commerceservice.controller;


import com.example.commerceservice.model.dto.CreateProductRequest;
import com.example.commerceservice.model.projection.ProductPreviewProjection;
import com.example.commerceservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/category/{categoryId}/previews")
    public List<ProductPreviewProjection> getProductPreviewsByCategory(@PathVariable UUID categoryId) {
        return productService.getProductPreviewsByCategory(categoryId);
    }
    @GetMapping("/search/{keyword}/previews")
    public List<ProductPreviewProjection> getProductPreviewsByKeyword(@PathVariable String keyword) {
        return productService.getProductPreviewsByKeyword(keyword);
    }
    @PutMapping("/create")
    public ResponseEntity<String> addProduct(@RequestBody CreateProductRequest product) {
        productService.createProduct(product);
        return ResponseEntity.ok("Tạo sản phẩm thành công!");
    }

}