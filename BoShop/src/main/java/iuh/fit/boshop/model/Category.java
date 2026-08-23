package iuh.fit.boshop.model;

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
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "category_id")
    String id;
    String name;
    String description;
    @Column(unique = true)
    String slug;
    Boolean status;

    @Column(name = "created_at", updatable = false)
    @CreatedDate
    Instant createdAt;
    @Column(name = "updated_at")
    @LastModifiedDate
    Instant updatedAt;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Product> products;
}
