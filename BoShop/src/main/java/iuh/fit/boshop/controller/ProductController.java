package iuh.fit.boshop.controller;

import iuh.fit.boshop.dto.request.CreateProductRequest;
import iuh.fit.boshop.dto.request.UpdateProductRequest;
import iuh.fit.boshop.dto.response.ApiResponse;
import iuh.fit.boshop.dto.response.ProductResponse;
import iuh.fit.boshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponse>> createProduct(CreateProductRequest request) {
        ProductResponse productResponse = productService.createProduct(request);
        return ResponseEntity.ok(new ApiResponse<>("Product created successfully", productResponse));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> getProductById(@PathVariable String id) {
        ProductResponse productResponse = productService.getProductById(id);
        return ResponseEntity.ok(new ApiResponse<>("Product retrieved successfully", productResponse));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> updateProduct(@PathVariable String id, UpdateProductRequest request) {
        ProductResponse productResponse = productService.updateProduct(id, request);
        return ResponseEntity.ok(new ApiResponse<>("Product updated successfully", productResponse));
    }


}
