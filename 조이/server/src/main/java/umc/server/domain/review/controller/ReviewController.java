package umc.server.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.review.dto.req.ReviewReqDTO;
import umc.server.domain.review.service.ReviewService;
import umc.server.global.apiPaylaod.ApiResponse;
import umc.server.global.apiPaylaod.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/{storeId}")
    public ApiResponse createReview(
            @PathVariable Long storeId,
            @RequestBody ReviewReqDTO.ReviewDTO request
    ) {
        reviewService.createReview(storeId, request);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK,"리뷰 작성 성공!");
    }
}

