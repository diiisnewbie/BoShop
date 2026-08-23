package iuh.fit.boshop.service;

import iuh.fit.boshop.dto.request.CreateProductRequest;
import iuh.fit.boshop.dto.request.CreateProductVariantRequest;
import iuh.fit.boshop.dto.request.UpdateProductVariantRequest;
import iuh.fit.boshop.dto.response.ProductVariantResponse;

import java.util.List;

public interface ProductVariantService {
    ProductVariantResponse createProductVariant(String productId, CreateProductVariantRequest request);
    ProductVariantResponse updateProductVariant(String variantId, UpdateProductVariantRequest request);
    List<ProductVariantResponse> getAllProductVariantsByProductId(String productId);
    ProductVariantResponse getProductVariantById(String variantId);
    void deleteProductVariant(String variantId);
}
