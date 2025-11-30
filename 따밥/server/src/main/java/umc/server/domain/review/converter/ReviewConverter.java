package umc.server.domain.review.converter;

import org.springframework.data.domain.Page;
import umc.server.domain.member.entity.Member;
import umc.server.domain.review.dto.req.ReviewReqDTO;
import umc.server.domain.review.dto.res.ReviewResDTO;
import umc.server.domain.review.entity.Review;
import umc.server.domain.store.entity.Store;

import java.time.LocalDate;

public class ReviewConverter {
    public static Review toReview(ReviewReqDTO.ReviewReq dto, Member member, Store store){
        return Review.builder()
                .member(member)
                .store(store)
                .description(dto.description())
                .photo(dto.photo())
                .score(dto.score())
                .build();
    }

    public static ReviewResDTO.reviewScoreDTO toReviewResDTO(Long reviewId, Double score){
        return ReviewResDTO.reviewScoreDTO.builder()
                .Score(score)
                .reviewId(reviewId)
                .build();
    }

    public static ReviewResDTO.ReviewPreViewListDTO toReviewPreViewListDTO(
            Page<Review> result
    ){
        return ReviewResDTO.ReviewPreViewListDTO.builder()
                .reviewList(result.getContent().stream()
                        .map(ReviewConverter::toReviewPreviewDTO)
                        .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .iSFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static ReviewResDTO.ReviewPreviewDTO toReviewPreviewDTO(Review review){
        return ReviewResDTO.ReviewPreviewDTO.builder()
                .ownerNickname(review.getMember().getNickname())
                .score(review.getScore())
                .body(review.getDescription())
                .createdAt(LocalDate.from(review.getCreatedAt()))
                .build();
    }
}
