package com.example.jerry.domain.controller;

import com.example.jerry.domain.dto.request.MissionCreateReqDto;
import com.example.jerry.domain.dto.request.MissionReqDto;
import com.example.jerry.domain.dto.response.MissionResDto;
import com.example.jerry.domain.service.MissionService;
import com.example.jerry.global.annotation.PageableFromOne;
import com.example.jerry.global.apiPayload.ApiResponse;
import com.example.jerry.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {

    private final MissionService missionService;

    @Operation(
            summary = "사용자가 수행 가능한 미션 조회",
            description = "지역 및 회원 정보를 기반으로 사용자가 수행 가능한 미션 리스트를 조회한다."
    )
    @GetMapping("/available")
    public ApiResponse<List<MissionResDto>> getAvailableMissions(
            @RequestBody MissionReqDto reqDto
    ) {
        List<MissionResDto> missions = missionService.getAvailableMissions(reqDto);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, missions);
    }


    @Operation(
            summary = "미션 생성",
            description = "특정 가게에 대한 미션을 생성한다."
    )
    @PostMapping
    public ApiResponse<MissionResDto> createMission(
            @RequestBody MissionCreateReqDto req
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                missionService.createMission(req)
        );
    }

    @Operation(
            summary = "특정 가게의 미션 목록 조회",
            description = "restaurantId에 해당하는 가게의 미션들을 page(1 이상) 단위로 10개씩 페이징 조회한다."
    )
    @GetMapping("/restaurant/{restaurantId}")
    public ApiResponse<MissionResDto.MissionPageDto> getMissionsByRestaurant(
            @PathVariable Integer restaurantId,
            @Parameter(description = "1 이상의 페이지 번호", example = "1")
            @PageableFromOne Pageable pageable
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                missionService.getMissionsByRestaurant(restaurantId, pageable)
        );
    }

}
