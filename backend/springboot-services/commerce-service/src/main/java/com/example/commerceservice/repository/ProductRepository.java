package com.example.commerceservice.repository;


import com.example.commerceservice.model.entity.Product;
import com.example.commerceservice.model.projection.ProductPreviewProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findByCategoryId(UUID categoryId);
    List<Product> findAll();
    @Query("""
    SELECT p.id AS id,
           p.name AS name,
           p.price AS price,
           (
               SELECT i.imageUrl
               FROM ProductImage i
               WHERE i.product.id = p.id AND i.isMain = true
           ) AS thumbnailUrl
    FROM Product p
    WHERE p.categoryId = :categoryId
""")
    List<ProductPreviewProjection> findProductPreviewsByCategory(@Param("categoryId") UUID categoryId);

    @Query("""
    SELECT p.id AS id,
           p.name AS name,
           p.price AS price,
           (
               SELECT i.imageUrl
               FROM ProductImage i
               WHERE i.product.id = p.id AND i.isMain = true
           ) AS thumbnailUrl
    FROM Product p
    WHERE p.storeId = :storeId
""")
    List<ProductPreviewProjection> findProductPreviewsByRestaurant(@Param("storeId") UUID restaurantId);

    @Query("""
    SELECT p.id AS id,
           p.name AS name,
           p.price AS price,
           (
               SELECT i.imageUrl
               FROM ProductImage i
               WHERE i.product.id = p.id AND i.isMain = true
           ) AS thumbnailUrl
    FROM Product p
    WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
       OR LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%'))
""")
    List<ProductPreviewProjection> searchProductPreviewsByKeyword(@Param("keyword") String keyword);

}