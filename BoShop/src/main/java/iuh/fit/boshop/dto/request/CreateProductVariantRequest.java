package iuh.fit.boshop.dto.request;

import java.math.BigDecimal;

public record CreateProductVariantRequest(
        String sku,
        BigDecimal price
){}
