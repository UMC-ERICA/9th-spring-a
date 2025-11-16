package umc.server.domain.store.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.review.dto.req.ReviewReqDTO;
import umc.server.domain.review.service.ReviewService;
import umc.server.domain.store.dto.req.StoreReqDTO;
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
    public ApiResponse<Void> register(
            @RequestBody StoreReqDTO.RegisterDTO request
    ){
        storeService.register(request);
        GeneralSuccessCode code = GeneralSuccessCode._CREATED;
        return ApiResponse.success(code, null);
    }

    @PostMapping("/{storeId}/reviews")
    public ApiResponse<Void> review(
            @PathVariable Long storeId,
            @RequestBody ReviewReqDTO.ReviewReq request
    ){
        reviewService.createReview(storeId, request);
        GeneralSuccessCode code = GeneralSuccessCode._CREATED;
        return ApiResponse.success(code, null);
    }
}
