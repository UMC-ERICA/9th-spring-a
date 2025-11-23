package umc.server.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.member.service.MemberService;
import umc.server.domain.review.dto.req.ReviewReqDTO;
import umc.server.domain.review.dto.res.ReviewResDTO;
import umc.server.domain.review.service.ReviewService;
import umc.server.domain.store.service.StoreService;
import umc.server.global.apiPaylaod.ApiResponse;
import umc.server.global.apiPaylaod.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final StoreService storeService;

    @PostMapping("/review/{storeId}")
    public ApiResponse createReview(
            @PathVariable Long storeId,
            @RequestBody ReviewReqDTO.WriteReviewReqDTO dto
    ) {
        ReviewResDTO.ReviewDTO reviewDTO= reviewService.createReview(storeId, dto);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK,"리뷰 작성 성공!");
    }
}

