package com.example.jerry.domain.service;
import com.example.jerry.domain.entity.Review;
import com.example.jerry.domain.entity.QReview;
import com.example.jerry.domain.repository.ReviewRepository;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {

    private final ReviewRepository reviewRepository;
    private final EntityManager em;

    // 검색 API
    @Override
    public List<Review> searchReview(
            Predicate predicate
    ){

        // JPA 세팅
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        // Q클래스 선언
        QReview review = QReview.review;

        return queryFactory
                .selectFrom(review)
                .where(predicate)
                .fetch();
    }
}
