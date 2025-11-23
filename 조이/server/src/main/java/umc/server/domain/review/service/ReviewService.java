package umc.server.domain.review.service;

import umc.server.domain.review.dto.req.ReviewReqDTO;
import umc.server.domain.review.dto.res.ReviewResDTO;

public interface ReviewService {
    public ReviewResDTO.ReviewDTO  createReview(Long storeId, ReviewReqDTO.WriteReviewReqDTO dto);
}
