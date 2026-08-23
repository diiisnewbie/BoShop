package iuh.fit.boshop.model;

import iuh.fit.boshop.model.enums.VariantStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.Instant;
@Entity
@Table(name = "product_variants")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EntityListeners(AuditingEntityListener.class)
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class ProductVariant {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.UUID)
    @Column(name = "product_variant_id")
    String id;
    BigDecimal price;
    @Enumerated(EnumType.STRING)
    VariantStatus status;
    Integer stock;
    String sku;

    @Column(name = "created_at", updatable = false)
    @CreatedDate
    Instant createdAt;
    @Column(name = "updated_at")
    @LastModifiedDate
    Instant updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    Product product;

    @OneToOne(mappedBy = "productVariant", cascade = CascadeType.ALL, orphanRemoval = true)
    Inventory inventory;
}
