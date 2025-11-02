package umc.server.domain.review.service;

import com.querydsl.core.BooleanBuilder;
import org.springframework.stereotype.Service;
import umc.server.domain.review.entity.QReview;
import umc.server.domain.review.entity.Review;
import umc.server.domain.review.repository.ReviewRepository;
import umc.server.domain.store.entity.QStore;
import umc.server.domain.store.entity.QStoreAddress;

import java.util.List;

@Service
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;
    public ReviewQueryService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> searchReview(
            String query,
            String type
    ) {
        // Q클래스 정의
        QReview review = QReview.review;

        // BooleanBuilder 정의
        BooleanBuilder builder = new BooleanBuilder();

        // Boolean Builder 사용

        // 동적 쿼리: 검색 조건
        if (type.equals("location")){
            builder.and(review.store.storeAddress.addr1.contains(query)
                    .or(review.store.storeAddress.addr2.contains(query))
                    .or(review.store.storeAddress.addr3.contains(query))
                    .or(review.store.storeAddress.addr4.contains(query)));
        }

        if (type.equals("score")){
            builder.and(review.score.goe(Double.parseDouble(query)));
        }
        if (type.equals("both")){
            // 기준 변환 예) 서울&4.5
            String firstQuery = query.split("&")[0];
            String secondQuery = query.split("&")[1];

            // 동적 쿼리
            builder.and(review.store.storeAddress.addr1.contains(firstQuery)
                    .or(review.store.storeAddress.addr2.contains(firstQuery))
                    .or(review.store.storeAddress.addr3.contains(firstQuery))
                    .or(review.store.storeAddress.addr4.contains(firstQuery)));
            builder.and(review.score.goe(Double.parseDouble(secondQuery)));
        }

        // Repository 사용 & 결과 매핑
        List<Review> reviewList = reviewRepository.searchReview(builder);

        return reviewList;
    }
}
