package iuh.fit.boshop.mapper;

import iuh.fit.boshop.dto.request.CreateProductVariantRequest;
import iuh.fit.boshop.dto.request.UpdateProductVariantRequest;
import iuh.fit.boshop.dto.response.ProductVariantResponse;
import iuh.fit.boshop.model.ProductVariant;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductVariantMapper {
    ProductVariant toProductVariant(CreateProductVariantRequest request);

    ProductVariantResponse toResponse(ProductVariant variant);

    void updateFromRequest(
            @MappingTarget ProductVariant variant,
            UpdateProductVariantRequest request
    );
}
