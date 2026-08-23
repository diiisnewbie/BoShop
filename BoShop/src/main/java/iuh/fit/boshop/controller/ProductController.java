package iuh.fit.boshop.controller;

import iuh.fit.boshop.dto.request.CreateProductRequest;
import iuh.fit.boshop.dto.request.UpdateProductRequest;
import iuh.fit.boshop.dto.response.ApiResponse;
import iuh.fit.boshop.dto.response.ProductResponse;
import iuh.fit.boshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponse>> createProduct(@RequestBody CreateProductRequest request) {

        ProductResponse productResponse = productService.createProduct(request);
        return ResponseEntity.ok(new ApiResponse<>("Product created successfully", productResponse));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getAllProducts() {
        List<ProductResponse> products = productService.getAll();
        return ResponseEntity.ok(new ApiResponse<>("Products retrieved successfully", products));
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
