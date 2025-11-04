package umc.server.domain.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import umc.server.domain.store.entity.Store;
import java.util.List;
@Repository
public interface StoreRepository  extends JpaRepository<Store, Long> {
}