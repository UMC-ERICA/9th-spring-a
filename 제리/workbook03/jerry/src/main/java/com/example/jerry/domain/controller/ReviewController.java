package com.example.jerry.domain.controller;

import com.example.jerry.domain.dto.request.ReviewReqDto;
import com.example.jerry.domain.dto.response.ReviewResDto;
import com.example.jerry.domain.service.ReviewService;
import com.example.jerry.global.apiPayload.ApiResponse;
import com.example.jerry.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import com.example.jerry.global.annotation.PageableFromOne;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "리뷰 작성", description = "회원이 특정 가게에 리뷰를 작성")
    @PostMapping
    public ApiResponse<ReviewResDto> createReview(@RequestBody ReviewReqDto req) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                reviewService.createReview(req)
        );
    }

    @Operation(
            summary = "내가 작성한 리뷰 목록 조회",
            description = "memberId로 내가 작성한 리뷰를 조회하며, page 쿼리 파라미터를 통해 페이지 번호(1 이상)를 전달한다."
    )
    @GetMapping("/member/{memberId}")
    public ApiResponse<ReviewResDto.ReviewPageDto> getMyReviews(
            @PathVariable Long memberId,
            @Parameter(description = "1 이상의 페이지 번호", example = "1")
            @PageableFromOne Pageable pageable
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                reviewService.getMyReviews(memberId, pageable)
        );
    }
}
