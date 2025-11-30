package umc.server.domain.review.converter;

import org.springframework.data.domain.Page;
import umc.server.domain.member.entity.Member;
import umc.server.domain.review.dto.req.ReviewReqDTO;
import umc.server.domain.review.dto.res.ReviewResDTO;
import umc.server.domain.review.entity.Review;

import java.time.LocalDate;

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
    // result -> DTO
    public static ReviewResDTO.ReviewPreViewListDTO toReviewPreviewListDTO(
            Page<Review> result
    ){
        return ReviewResDTO.ReviewPreViewListDTO.builder()
                .reviewList(result.getContent().stream()
                        .map(ReviewConverter::toReviewPreviewDTO)
                        .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static ReviewResDTO.ReviewPreViewDTO toReviewPreviewDTO(
            Review review
    ){
        return ReviewResDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getStar())
                .body(review.getReviewContext())
                .createdAt(LocalDate.from(review.getCreatedAt()))
                .build();
    }
}
