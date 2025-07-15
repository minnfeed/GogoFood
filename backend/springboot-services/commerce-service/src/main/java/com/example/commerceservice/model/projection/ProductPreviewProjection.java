package com.example.commerceservice.model.projection;

import java.math.BigDecimal;
import java.util.UUID;

public interface ProductPreviewProjection {
    UUID getId();
    String getName();
    BigDecimal getPrice();
    String getThumbnailUrl(); // ánh xạ ảnh đầu tiên
}
