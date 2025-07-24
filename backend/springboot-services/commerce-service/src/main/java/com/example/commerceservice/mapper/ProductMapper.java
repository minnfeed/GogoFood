package com.example.commerceservice.mapper;

import com.example.commerceservice.model.dto.ProductDetailDto;
import com.example.commerceservice.model.dto.ProductImageDto;
import com.example.commerceservice.model.entity.Product;
import com.example.commerceservice.model.entity.ProductImage;
import com.example.commerceservice.model.entity.ProductReview;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "category.name", target = "categoryName")
    @Mapping(target = "averageRating", expression = "java(getAvgRating(product.getReviews()))")
    @Mapping(target = "totalReviews", expression = "java(product.getReviews() != null ? product.getReviews().size() : 0)")
    ProductDetailDto toDto(Product product);

    List<ProductImageDto> mapImages(List<ProductImage> images);

    @Mapping(source = "imageUrl", target = "imageUrl")
    @Mapping(source = "main", target = "isMain", qualifiedByName = "booleanToString")
    ProductImageDto toImageDto(ProductImage image);

    default double getAvgRating(List<ProductReview> reviews) {
        if (reviews == null || reviews.isEmpty()) return 0.0;
        return reviews.stream().mapToInt(ProductReview::getRating).average().orElse(0.0);
    }

    @Named("booleanToString")
    default String booleanToString(boolean value) {
        return Boolean.toString(value);
    }
}
