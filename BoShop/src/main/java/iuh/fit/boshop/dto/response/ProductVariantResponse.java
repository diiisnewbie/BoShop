package iuh.fit.boshop.dto.response;

import iuh.fit.boshop.model.enums.VariantStatus;

import java.math.BigDecimal;

public record ProductVariantResponse (
    String id,
    String productId,
    String sku,
    BigDecimal price,
    VariantStatus status
){}


