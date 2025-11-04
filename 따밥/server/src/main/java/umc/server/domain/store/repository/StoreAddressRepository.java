package umc.server.domain.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.domain.store.entity.StoreAddress;

public interface StoreAddressRepository extends JpaRepository<StoreAddress, Long> {
}
