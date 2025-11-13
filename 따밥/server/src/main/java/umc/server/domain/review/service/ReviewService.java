package umc.server.domain.review.service;

import umc.server.domain.review.dto.req.ReviewReqDTO;

public interface ReviewService {
    void createReview(Long storeId, ReviewReqDTO.ReviewReq request);
}
