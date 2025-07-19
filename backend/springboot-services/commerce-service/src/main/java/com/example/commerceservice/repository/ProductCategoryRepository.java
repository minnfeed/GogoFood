package com.example.commerceservice.repository;

import com.example.commerceservice.model.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, UUID> {
}
