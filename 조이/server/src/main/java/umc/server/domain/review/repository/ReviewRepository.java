package umc.server.domain.review.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.domain.member.enums.Type;
import umc.server.domain.review.entity.Review;
import umc.server.domain.store.entity.Store;

import java.util.List;


public interface ReviewRepository extends JpaRepository<Review, Long>{
    Page<Review> findAllByStore(Store store, PageRequest pageRequest);

    Store StoreId(Long storeId);

    List<Review> store(Store store);

    Store storeId(Long storeId);
}
