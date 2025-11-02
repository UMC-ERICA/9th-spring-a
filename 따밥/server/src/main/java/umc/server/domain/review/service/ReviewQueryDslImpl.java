package umc.server.domain.review.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import umc.server.domain.review.entity.QReview;
import umc.server.domain.review.entity.Review;
import umc.server.domain.review.repository.ReviewRepository;
import umc.server.domain.store.entity.QStore;
import umc.server.domain.store.entity.QStoreAddress;

import java.util.List;

@Service
public class ReviewQueryDslImpl implements ReviewQueryDsl {

    private final EntityManager em;

    public ReviewQueryDslImpl(EntityManager em) {
        this.em = em;
    }

    // 검색 API
    @Override
    public List<Review> searchReview(Predicate predicate) {
        // JPA 세팅
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        // Q클래스 선언
        QReview review = QReview.review;

        return queryFactory
                .selectFrom(review)
                .where(predicate)
                .orderBy(review.createdAt.desc())
                .fetch();
    }

}
