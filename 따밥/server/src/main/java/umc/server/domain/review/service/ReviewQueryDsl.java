package umc.server.domain.review.service;

import com.querydsl.core.types.Predicate;
import umc.server.domain.review.entity.Review;

import java.util.List;

public interface ReviewQueryDsl {
    List<Review> searchReview(Predicate predicate);
}
