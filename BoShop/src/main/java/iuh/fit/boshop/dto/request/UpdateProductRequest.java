package iuh.fit.boshop.dto.request;

import iuh.fit.boshop.model.enums.ProductStatus;

public record UpdateProductRequest (
    String name,
    String description,
    String slug,
    ProductStatus status
){}
