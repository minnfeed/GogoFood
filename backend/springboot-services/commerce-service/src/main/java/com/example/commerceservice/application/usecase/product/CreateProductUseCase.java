package com.example.commerceservice.application.usecase.product;

import com.example.commerceservice.web.dto.ProductDTO;

public interface CreateProductUseCase {
    ProductDTO create(CreateProductCommand command);
}
