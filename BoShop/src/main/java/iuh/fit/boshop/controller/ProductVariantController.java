package iuh.fit.boshop.controller;

import iuh.fit.boshop.dto.request.CreateProductVariantRequest;
import iuh.fit.boshop.dto.request.UpdateProductVariantRequest;
import iuh.fit.boshop.dto.response.ApiResponse;
import iuh.fit.boshop.dto.response.ProductVariantResponse;
import iuh.fit.boshop.service.ProductVariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products/{productId}/variants")
@RequiredArgsConstructor
public class ProductVariantController {
    private final ProductVariantService productVariantService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProductVariantResponse>> createProductVariant(@PathVariable String productId, CreateProductVariantRequest request) {
        ProductVariantResponse productVariantResponse = productVariantService.createProductVariant(productId, request);
        return ResponseEntity.ok(new ApiResponse<>("Product variant created successfully", productVariantResponse));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductVariantResponse>>> getAllProductVariantsByProductId(@PathVariable String productId) {
        var productVariantResponses = productVariantService.getAllProductVariantsByProductId(productId);
        return ResponseEntity.ok(new ApiResponse<>("Product variants retrieved successfully", productVariantResponses));
    }


    @GetMapping("/{variantId}")
    public ResponseEntity<ApiResponse<ProductVariantResponse>> getProductVariantById(@PathVariable String variantId) {
        ProductVariantResponse productVariantResponse = productVariantService.getProductVariantById(variantId);
        return ResponseEntity.ok(new ApiResponse<>("Product variant retrieved successfully", productVariantResponse));
    }

    @PatchMapping("/{variantId}")
    public ResponseEntity<ApiResponse<ProductVariantResponse>> updateProductVariant(@PathVariable String variantId, @RequestBody UpdateProductVariantRequest request) {
        ProductVariantResponse productVariantResponse = productVariantService.updateProductVariant(variantId, request);
        return ResponseEntity.ok(new ApiResponse<>("Product variant updated successfully", productVariantResponse));
    }

    @DeleteMapping("/{variantId}")
    public ResponseEntity<ApiResponse<Void>> deleteProductVariant(@PathVariable String variantId) {
        productVariantService.deleteProductVariant(variantId);
        return ResponseEntity.ok(new ApiResponse<>("Product variant deleted successfully", null));
    }
}
