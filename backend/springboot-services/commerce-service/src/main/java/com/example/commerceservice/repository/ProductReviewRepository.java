package com.example.commerceservice.repository;

import com.example.commerceservice.model.entity.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ProductReviewRepository extends JpaRepository<ProductReview, UUID> {}
