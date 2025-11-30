package umc.server.domain.store.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import umc.server.domain.member.enums.Type;
import umc.server.domain.review.entity.Review;
import umc.server.domain.store.entity.Store;
import java.util.List;
import java.util.Optional;

public interface StoreRepository  extends JpaRepository<Store, Long> {

}
