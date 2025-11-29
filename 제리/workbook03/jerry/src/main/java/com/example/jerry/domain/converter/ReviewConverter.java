package com.example.jerry.domain.converter;

import com.example.jerry.domain.dto.response.ReviewResDto;
import com.example.jerry.domain.entity.Review;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewConverter {

    public static ReviewResDto.ReviewPageDto toMyReviewPage(Page<Review> page) {
        return ReviewResDto.ReviewPageDto.builder()
                .reviewList(
                        page.getContent()
                                .stream()
                                .map(ReviewResDto::from)
                                .toList()
                )
                .listSize(page.getNumberOfElements())
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }
}
