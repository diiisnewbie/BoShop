package iuh.fit.boshop.service.impl;

import iuh.fit.boshop.dto.request.CreateProductRequest;
import iuh.fit.boshop.dto.request.CreateProductVariantRequest;
import iuh.fit.boshop.dto.request.UpdateProductVariantRequest;
import iuh.fit.boshop.dto.response.ProductVariantResponse;
import iuh.fit.boshop.exceptions.ProductNotFoundException;
import iuh.fit.boshop.exceptions.ProductVariantNotFoundException;
import iuh.fit.boshop.exceptions.SkuAlreadyExistsException;
import iuh.fit.boshop.mapper.ProductVariantMapper;
import iuh.fit.boshop.model.Product;
import iuh.fit.boshop.model.ProductVariant;
import iuh.fit.boshop.repository.ProductRepository;
import iuh.fit.boshop.repository.ProductVariantRepository;
import iuh.fit.boshop.service.ProductVariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductVariantServiceImpl implements ProductVariantService {

    private final ProductVariantRepository productVariantRepository;
    private final ProductVariantMapper productVariantMapper;
    private final ProductRepository productRepository;

    @Override
    public ProductVariantResponse createProductVariant(String productId, CreateProductVariantRequest request) {
        if (productVariantRepository.existsBySku(request.sku())) {
            throw new SkuAlreadyExistsException(request.sku());
        }

        ProductVariant variant = productVariantMapper.toProductVariant(request);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        variant.setProduct(product);

        ProductVariant savedVariant = productVariantRepository.save(variant);

        return productVariantMapper.toResponse(savedVariant);
    }

    @Override
    public ProductVariantResponse updateProductVariant(String variantId, UpdateProductVariantRequest request) {
        ProductVariant variant = productVariantRepository
                .findById(variantId)
                .orElseThrow(() ->
                        new ProductVariantNotFoundException(variantId)
                );

        if (!variant.getSku().equals(request.sku())
                && productVariantRepository.existsBySku(request.sku())) {

            throw new SkuAlreadyExistsException(request.sku());
        }

        productVariantMapper.updateFromRequest(variant, request);

        ProductVariant updated =
                productVariantRepository.save(variant);

        return productVariantMapper.toResponse(updated);
    }

    @Override
    public List<ProductVariantResponse> getAllProductVariantsByProductId(String productId) {
        return productVariantRepository.findAllByProductId(productId)
                .stream()
                .map(productVariantMapper::toResponse)
                .toList();
    }

    @Override
    public ProductVariantResponse getProductVariantById(String variantId) {
        ProductVariant variant = productVariantRepository
                .findById(variantId)
                .orElseThrow(() ->
                        new ProductVariantNotFoundException(variantId)
                );

        return productVariantMapper.toResponse(variant);
    }

    @Override
    public void deleteProductVariant(String variantId) {
        ProductVariant variant = productVariantRepository
                .findById(variantId)
                .orElseThrow(() ->
                        new ProductVariantNotFoundException(variantId)
                );

        productVariantRepository.delete(variant);
    }


}
