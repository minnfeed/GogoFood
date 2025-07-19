package com.example.commerceservice.repository;

import com.example.commerceservice.model.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, UUID> {}
