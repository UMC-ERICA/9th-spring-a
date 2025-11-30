package umc.server.domain.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.domain.store.entity.Store;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Optional<Store> findByStoreName(String storeName);
}
