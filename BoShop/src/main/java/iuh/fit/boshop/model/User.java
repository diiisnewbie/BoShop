package iuh.fit.boshop.model;

import iuh.fit.boshop.model.enums.Role;
import iuh.fit.boshop.model.enums.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@EntityListeners(AuditingEntityListener.class)
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    String id;
    String password;
    String email;
    @Enumerated(EnumType.STRING)
    UserStatus status;
    @Enumerated(EnumType.STRING)
    Role role;
    @CreatedDate
    Instant createdAt;


}
