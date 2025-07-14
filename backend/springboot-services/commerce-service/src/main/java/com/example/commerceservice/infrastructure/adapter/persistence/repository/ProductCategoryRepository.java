package com.example.commerceservice.infrastructure.adapter.persistence.repository;

import com.example.commerceservice.domain.model.entity.Product;
import com.example.commerceservice.domain.model.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, UUID> {
    Optional<ProductCategory> findById(UUID id);
    List<ProductCategory> findAll();
    List<Product> findByCategory(UUID categoryId);
    void deleteById(UUID id);
}
