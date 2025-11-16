package com.example.jerry.domain.service;

import com.example.jerry.domain.dto.request.ReviewReqDto;
import com.example.jerry.domain.dto.response.ReviewResDto;
import com.example.jerry.domain.entity.Member;
import com.example.jerry.domain.entity.Restaurant;
import com.example.jerry.domain.entity.Review;
import com.example.jerry.domain.exception.TestException;
import com.example.jerry.domain.exception.code.ReviewErrorCode;
import com.example.jerry.domain.repository.MemberRepository;
import com.example.jerry.domain.repository.RestaurantRepository;
import com.example.jerry.domain.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final RestaurantRepository restaurantRepository;

    public ReviewResDto createReview(ReviewReqDto req) {

        // 1. Member 존재 여부 확인
        Member member = memberRepository.findById(req.getMemberId())
                .orElseThrow(() -> new TestException(ReviewErrorCode.MEMBER_NOT_FOUND));

        // 2. Restaurant 존재 여부 확인
        Restaurant restaurant = restaurantRepository.findById(req.getRestaurantId())
                .orElseThrow(() -> new TestException(ReviewErrorCode.RESTAURANT_NOT_FOUND));

        // 3. 별점 검증
        if (req.getRating() < 1 || req.getRating() > 5) {
            throw new TestException(ReviewErrorCode.INVALID_RATING);
        }

        // 4. Review 생성
        Review review = new Review(
                req.getContent(),
                req.getRating(),
                req.getPhoto(),
                member,
                restaurant
        );

        reviewRepository.save(review);

        return ReviewResDto.from(review);
    }
}