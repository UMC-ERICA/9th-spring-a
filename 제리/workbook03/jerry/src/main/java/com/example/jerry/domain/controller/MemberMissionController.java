package com.example.jerry.domain.controller;

import com.example.jerry.domain.dto.request.MissionChallengeReqDto;
import com.example.jerry.domain.dto.response.MemberMissionResDto;
import com.example.jerry.domain.service.MemberMissionService;
import com.example.jerry.global.config.PageableConfig.PageableFromOne;
import com.example.jerry.global.apiPayload.ApiResponse;
import com.example.jerry.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member-missions")
public class MemberMissionController {

    private final MemberMissionService memberMissionService;

    @Operation(
            summary = "미션 도전",
            description = "회원이 특정 미션에 도전한다."
    )
    @PostMapping("/challenge")
    public ApiResponse<MemberMissionResDto> challengeMission(
            @RequestBody MissionChallengeReqDto req
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                memberMissionService.challengeMission(req)
        );
    }

    @Operation(
            summary = "미션 완료 처리",
            description = "특정 회원의 특정 미션을 완료 상태로 변경한다."
    )
    @PatchMapping("/{memberId}/missions/{missionId}/clear")
    public ApiResponse<MemberMissionResDto> clearMission(
            @PathVariable Long memberId,
            @PathVariable Long missionId
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                memberMissionService.clearMission(memberId, missionId)
        );
    }

    @Operation(
            summary = "내가 진행중인 미션 목록 조회",
            description = "memberId 기준으로 clear = false 인 진행중인 미션들을 page(1 이상) 단위로 10개씩 페이징 조회한다."
    )
    @GetMapping("/{memberId}/in-progress")
    public ApiResponse<MemberMissionResDto.MemberMissionPageDto> getInProgressMissions(
            @PathVariable Long memberId,
            @Parameter(description = "1 이상의 페이지 번호", example = "1")
            @PageableFromOne Pageable pageable
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                memberMissionService.getInProgressMissions(memberId, pageable)
        );
    }
}
