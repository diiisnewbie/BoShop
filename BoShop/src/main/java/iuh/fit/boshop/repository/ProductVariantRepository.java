package iuh.fit.boshop.repository;

import iuh.fit.boshop.model.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, String> {
    boolean  existsBySku(String sku);
    Optional<ProductVariant> findBySku(String sku);
    List<ProductVariant> findAllByProductId(String productId);

}
