package com.example.jerry.domain.controller;

import com.example.jerry.domain.dto.request.ReviewReqDto;
import com.example.jerry.domain.dto.response.ReviewResDto;
import com.example.jerry.domain.service.ReviewService;
import com.example.jerry.global.apiPayload.ApiResponse;
import com.example.jerry.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 작성 API
    @PostMapping
    public ApiResponse<ReviewResDto> createReview(@RequestBody ReviewReqDto req) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                reviewService.createReview(req)
        );
    }
}
