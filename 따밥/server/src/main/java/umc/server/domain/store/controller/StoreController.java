package umc.server.domain.store.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.review.dto.req.ReviewReqDTO;
import umc.server.domain.review.dto.res.ReviewResDTO;
import umc.server.domain.review.service.ReviewService;
import umc.server.domain.store.dto.req.StoreReqDTO;
import umc.server.domain.store.dto.res.StoreResDTO;
import umc.server.domain.store.service.StoreService;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {
    private final StoreService storeService;
    private final ReviewService reviewService;
    // 매장 등록
    @PostMapping("/register")
    public ApiResponse<StoreResDTO.RegisterDTO> register(
            @RequestBody @Valid StoreReqDTO.RegisterDTO request
    ){
        return ApiResponse.success(
                GeneralSuccessCode._CREATED,
                storeService.register(request)
        );
    }

    @PostMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.reviewScoreDTO > review(
            @PathVariable Long storeId,
            @RequestBody ReviewReqDTO.ReviewReq request
    ){

        return ApiResponse.success(GeneralSuccessCode._CREATED,
                reviewService.createReview(storeId, request));
    }
}
