package umc.server.domain.review.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.server.domain.review.dto.res.ReviewResDTO;
import umc.server.domain.review.entity.Review;
import umc.server.domain.review.exception.code.ReviewSuccessCode;
import umc.server.domain.review.service.ReviewQueryService;
import umc.server.domain.review.service.ReviewService;
import umc.server.global.apiPayload.ApiResponse;

import java.util.List;

@RestController
public class ReviewController implements ReviewControllerDocs {
    private final ReviewQueryService reviewQueryService;
    private final ReviewService reviewService;

    public ReviewController(ReviewQueryService reviewQueryService, ReviewService reviewService) {
        this.reviewQueryService = reviewQueryService;
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews/search")
    public List<Review> searchReview(
            @RequestParam String query,
            @RequestParam String type
    ){
        List<Review> result = reviewQueryService.searchReview(query, type);
        return result;
    }

    // 가게별 리뷰 조회
    @GetMapping("/reviews")
    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getStoreReviews(
            @RequestParam String storeName,
            @RequestParam(defaultValue = "1") Integer page
    ){
        return ApiResponse.success(
                ReviewSuccessCode.REVIEW_FOUND,
                reviewService.findStoreReview(storeName, page));
    }
}
