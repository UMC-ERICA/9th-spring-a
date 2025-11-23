package umc.server.domain.review.converter;

import umc.server.domain.member.entity.Member;
import umc.server.domain.review.dto.req.ReviewReqDTO;
import umc.server.domain.review.dto.res.ReviewResDTO;
import umc.server.domain.review.entity.Review;

import static umc.server.domain.member.entity.QMember.member;

public class ReviewConverter {
    //entity  -> dto
    public static ReviewResDTO.ReviewDTO toReviewDTO(
            Review review
    ){
        return ReviewResDTO.ReviewDTO.builder()
                .memberName(review.getName())
                .createdAt(review.getCreatedAt())
                .reviewContext(review.getReviewContext())
                .title(review.getTitle())
                .star(review.getStar())
                .build();
    }

    // DTO -> entity
    public static Review toReview(
            ReviewReqDTO.WriteReviewReqDTO dto, Member member
    ){
        return Review.builder()
                .reviewContext(dto.getContent())
                .title(dto.getTitle())
                .member(member)
                .star(dto.getStar())
                .build();
    }
}
