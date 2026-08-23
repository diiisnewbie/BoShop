package iuh.fit.boshop.dto.request;

import java.math.BigDecimal;

public record CreateProductRequest (
    String name,
    String description,
    String slug
){}
