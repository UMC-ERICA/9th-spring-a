package umc.server.domain.review.service;

import umc.server.domain.review.dto.req.ReviewReqDTO;

public interface ReviewService {
    public void createReview(Long storeId, ReviewReqDTO.ReviewDTO request);
}
