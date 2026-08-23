package iuh.fit.boshop.service.impl;

import iuh.fit.boshop.dto.request.CreateProductRequest;
import iuh.fit.boshop.dto.request.UpdateProductRequest;
import iuh.fit.boshop.dto.response.ProductResponse;
import iuh.fit.boshop.exceptions.ProductNotFoundException;
import iuh.fit.boshop.exceptions.SlugAlreadyExistsException;
import iuh.fit.boshop.mapper.ProductMapper;
import iuh.fit.boshop.model.Product;
import iuh.fit.boshop.repository.ProductRepository;
import iuh.fit.boshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponse createProduct(CreateProductRequest request) {

        log.info("Creating product with name: {}, slug: {}", request.name(), request.slug());
        if(productRepository.existsBySlug(request.slug())) {
            throw new SlugAlreadyExistsException("Slug already exists: " + request.slug());
        }

        Product product = Product.builder()
                .name(request.name())
                .description(request.description())
                .slug(request.slug())
                .build();

        Product savedProduct = productRepository.save(product);

        return productMapper.toProductResponse(savedProduct);
    }

    @Override
    public ProductResponse getProductById(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        return productMapper.toProductResponse(product);
    }

    @Override
    public List<ProductResponse> getAll() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(productMapper::toProductResponse)
                .toList();
    }

    @Override
    public ProductResponse updateProduct(String id, UpdateProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        if(!product.getSlug().equals(request.slug()) && productRepository.existsBySlug(request.slug())) {
            throw new SlugAlreadyExistsException("Slug already exists: " + request.slug());
        }

        productMapper.updateProductFromRequest(product, request);
        Product updatedProduct = productRepository.save(product);

        return productMapper.toProductResponse(updatedProduct);
    }

    @Override
    public void deleteProduct(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        productRepository.delete(product);
    }
}
