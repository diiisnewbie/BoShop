package iuh.fit.boshop.dto.response;

import iuh.fit.boshop.model.enums.ProductStatus;

import java.time.Instant;

public record ProductResponse(
        String id,
        String name,
        String description,
        String slug,
        ProductStatus status,
        Instant createdAt,
        Instant updatedAt
) {
}
