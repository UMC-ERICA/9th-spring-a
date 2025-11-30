package umc.server.domain.review.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.member.service.MemberService;
import umc.server.domain.review.dto.req.ReviewReqDTO;
import umc.server.domain.review.dto.res.ReviewResDTO;
import umc.server.domain.review.entity.Review;
import umc.server.domain.review.exception.code.ReviewSuccessCode;
import umc.server.domain.review.service.ReviewService;
import umc.server.domain.store.entity.Store;
import umc.server.domain.store.repository.StoreRepository;
import umc.server.domain.store.service.StoreService;
import umc.server.global.apiPaylaod.ApiResponse;
import umc.server.global.apiPaylaod.code.GeneralSuccessCode;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final StoreService storeService;
    private final StoreRepository storeRepository;

    // api설명작성
    @Operation(
            summary = "가게의 리뷰 목록 조회 API By 마크 (개발 중)",
            description = "특정 가게의 리뷰를 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    // 코드,응답메세지 작성
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })

    @PostMapping("/review/{storeId}")
    public ApiResponse createReview(
            @PathVariable Long storeId,
            @RequestBody ReviewReqDTO.WriteReviewReqDTO dto
    ) {
        ReviewResDTO.ReviewDTO reviewDTO= reviewService.createReview(storeId, dto);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK,"리뷰 작성 성공!");
    }

//    @GetMapping("/reviews/search")
//    public List<Review> searchReview(
//            @RequestParam String filter,
//            @RequestParam String type
//    )throws Exception{
//        List<Review> result=reviewService.serchReview(filter, type);
//        return result;
//    }

    // 가게의 리뷰 목록 조회
    @GetMapping("/reviews")
    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getReviews(
            @RequestParam Long storeId,
            @RequestParam Integer page
    ){
        ReviewSuccessCode code = ReviewSuccessCode.FOUND;
        return ApiResponse.onSuccess(code, reviewService.findReview(storeId));
    }


}

