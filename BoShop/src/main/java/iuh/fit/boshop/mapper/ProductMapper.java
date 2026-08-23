package iuh.fit.boshop.mapper;

import iuh.fit.boshop.dto.request.UpdateProductRequest;
import iuh.fit.boshop.dto.response.ProductResponse;
import iuh.fit.boshop.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponse toProductResponse(Product product);


    void updateProductFromRequest(@MappingTarget Product product, UpdateProductRequest request);
}