package iuh.fit.boshop.model;

import iuh.fit.boshop.model.enums.AddressType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "addresses")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "address_id")
    String id;
    String phone;
    String street;
    String city;
    String province;
    @Enumerated(EnumType.STRING)
    AddressType type; //SHPPING, BILLING

    @ManyToOne
    @JoinColumn(name = "user_id")
    UserProfile userProfile;
}
