package umc.server.domain.review.service;

import umc.server.domain.review.dto.req.ReviewReqDTO;
import umc.server.domain.review.dto.res.ReviewResDTO;
import umc.server.domain.review.entity.Review;

import java.util.List;

public interface ReviewService {

    public ReviewResDTO.ReviewDTO  createReview(Long storeId, ReviewReqDTO.WriteReviewReqDTO dto);
    ReviewResDTO.ReviewPreViewListDTO findReview(Long storeId);
    ReviewResDTO.ReviewPreViewListDTO getStorebyReview(Long storeId);


}
