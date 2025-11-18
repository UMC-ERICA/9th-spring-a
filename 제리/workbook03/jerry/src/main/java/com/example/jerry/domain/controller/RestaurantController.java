package com.example.jerry.domain.controller;

import com.example.jerry.domain.dto.request.RestaurantReqDto;
import com.example.jerry.domain.dto.response.RestaurantResDto;
import com.example.jerry.domain.service.RestaurantService;
import com.example.jerry.global.apiPayload.ApiResponse;
import com.example.jerry.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    // 레스토랑 생성
    @PostMapping
    public ApiResponse<RestaurantResDto> create(@RequestBody RestaurantReqDto req) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                restaurantService.createRestaurant(req)
        );
    }

    // 레스토랑 조회
    @GetMapping("/{id}")
    public ApiResponse<RestaurantResDto> get(@PathVariable Integer id) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                restaurantService.getRestaurant(id)
        );
    }
}