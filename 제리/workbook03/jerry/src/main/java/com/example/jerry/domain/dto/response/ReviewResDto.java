package com.example.jerry.domain.dto.response;

import com.example.jerry.domain.entity.Review;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReviewResDto {

    private Long reviewId;
    private String content;
    private Byte rating;
    private String photo;
    private LocalDateTime createdAt;

    private Long memberId;
    private Integer restaurantId;

    public static ReviewResDto from(Review review) {
        return ReviewResDto.builder()
                .reviewId(review.getReviewId())
                .content(review.getContent())
                .rating(review.getRating())
                .photo(review.getPhoto())
                .createdAt(review.getCreatedAt())
                .memberId(review.getMember().getMemberId())
                .restaurantId(review.getRestaurant().getRestaurantsId())
                .build();
    }
}