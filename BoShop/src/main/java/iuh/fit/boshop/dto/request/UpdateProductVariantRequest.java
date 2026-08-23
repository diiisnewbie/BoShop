package iuh.fit.boshop.dto.request;

import iuh.fit.boshop.model.enums.VariantStatus;

import java.math.BigDecimal;

public record UpdateProductVariantRequest(
        String sku,

        BigDecimal price,

        VariantStatus status
){}
