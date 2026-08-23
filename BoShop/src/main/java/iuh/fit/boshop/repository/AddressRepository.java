package iuh.fit.boshop.repository;

import iuh.fit.boshop.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AddressRepository extends JpaRepository<Address, String> {
}
