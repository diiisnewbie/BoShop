package iuh.fit.boshop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Entity
@Table(name = "inventory")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "inventory_id")
    String id;
    int quantity;
    int reserved;

    @LastModifiedDate
    Instant updatedAt;

    @OneToOne
    @JoinColumn(name = "product_variant_id")
    ProductVariant productVariant;
}
