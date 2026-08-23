package iuh.fit.boshop.service;

import iuh.fit.boshop.dto.request.CreateProductRequest;
import iuh.fit.boshop.dto.request.UpdateProductRequest;
import iuh.fit.boshop.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(CreateProductRequest request);
    ProductResponse getProductById(String id);
    List<ProductResponse> getAll();

    ProductResponse updateProduct(String id, UpdateProductRequest request);

    void deleteProduct(String id);
}
