package umc.server.domain.review.converter;

import umc.server.domain.member.entity.Member;
import umc.server.domain.review.dto.req.ReviewReqDTO;
import umc.server.domain.review.dto.res.ReviewResDTO;
import umc.server.domain.review.entity.Review;
import umc.server.domain.store.entity.Store;

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
}
