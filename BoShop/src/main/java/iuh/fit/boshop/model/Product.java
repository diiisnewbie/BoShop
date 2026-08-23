package iuh.fit.boshop.model;

import iuh.fit.boshop.model.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "product_id")
    String id;
    String name;
    String description;
    String brand;
    @Enumerated(EnumType.STRING)
    ProductStatus status;
    @Column(unique = true)
    String slug;

    @Column(name = "created_at", updatable = false)
    @CreatedDate
    Instant createdAt;
    @Column(name = "updated_at")
    @LastModifiedDate
    Instant updatedAt;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    List<ProductVariant> productVariants;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;
}
