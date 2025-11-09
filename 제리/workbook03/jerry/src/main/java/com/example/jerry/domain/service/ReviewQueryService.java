package com.example.jerry.domain.service;

import com.example.jerry.domain.entity.QReview;
import com.example.jerry.domain.entity.Review;
import com.example.jerry.domain.repository.ReviewRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;

    public List<Review> searchReview(String type, String query) {

        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();

        if (type.equals("address")) {
            builder.and(review.restaurant.address.address.contains(query));
        }
        if (type.equals("star")) {
            builder.and(review.rating.goe(Float.parseFloat(query)));
        }
        if (type.equals("both")) {

            String firstQuery = query.split("&")[0];
            String secondQuery = query.split("&")[1];

            builder.and(review.restaurant.address.address.contains(firstQuery));
            builder.and(review.rating.goe(Float.parseFloat(secondQuery)));
        }

        List<Review> reviewList = reviewRepository.searchReview(builder);

        return reviewList;
    }
}
