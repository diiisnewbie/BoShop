package iuh.fit.boshop.model;

import iuh.fit.boshop.model.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "user_profiles")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_profile_id")
    String id;
    @Column(name = "avatar_url")
    String avatarUrl;
    @Column(name = "full_name")
    String fullname;
    String phone;
    @Enumerated(EnumType.STRING)
    Gender gender;
    @Column(name = "date_of_birth")
    LocalDate dateOfBirth;
    @Column(name = "updated_at")
    @LastModifiedDate
    Instant updatedAt;

    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Address> address;
}
