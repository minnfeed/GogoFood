package com.example.commerceservice.service.impl;

import com.example.commerceservice.client.RestaurantServiceClient;
import com.example.commerceservice.config.CurrentUserProvider;
import com.example.commerceservice.model.dto.CreateProductRequest;
import com.example.commerceservice.model.dto.ProductImageRequest;
import com.example.commerceservice.model.entity.Product;
import com.example.commerceservice.model.entity.ProductImage;
import com.example.commerceservice.model.projection.ProductPreviewProjection;
import com.example.commerceservice.repository.ProductRepository;
import com.example.commerceservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    CurrentUserProvider currentUserProvider;
    @Autowired
    RestaurantServiceClient restaurantServiceClient;

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductPreviewProjection> getProductPreviewsByCategory(UUID categoryId) {
        return productRepository.findProductPreviewsByCategory(categoryId);
    }
    @Override
    public List<ProductPreviewProjection> getProductPreviewsByKeyword(String keyword) {
        return productRepository.searchProductPreviewsByKeyword(keyword);
    }

    @Override
    public void createProduct(CreateProductRequest request) {
        UUID storeID = restaurantServiceClient.getRestaurantIdByUserId(currentUserProvider.getCurrentUserId());
        Product  product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCategoryId(request.getCategoryId());
        product.setStoreId(storeID);
        product.setStatus(request.getStatus());
        List<ProductImage> images = new ArrayList<>();
        for (ProductImageRequest url : request.getImages()) {
            ProductImage img = new ProductImage();
            img.setImageUrl(String.valueOf(url.getImageUrl()));
            img.setMain(url.isMainBoolean());
            img.setProduct(product);
            images.add(img);
        }
        product.setImages(images);
        productRepository.save(product);
    }
}
