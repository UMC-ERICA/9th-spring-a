package com.example.jerry.domain.controller;

import com.example.jerry.domain.dto.response.MemberMissionResDto;
import com.example.jerry.domain.service.MemberMissionService;
import com.example.jerry.global.apiPayload.ApiResponse;
import com.example.jerry.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member-missions")
public class MemberMissionController {

    private final MemberMissionService memberMissionService;

    // 진행 중
    @GetMapping("/{memberId}/uncleared")
    public ApiResponse<?> getUncleared(
            @PathVariable Long memberId,
            Pageable pageable
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                memberMissionService.getUnclearedMissions(memberId, pageable)
        );
    }

    // 완료된 미션
    @GetMapping("/{memberId}/cleared")
    public ApiResponse<?> getCleared(
            @PathVariable Long memberId,
            Pageable pageable
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                memberMissionService.getClearedMissions(memberId, pageable)
        );
    }

    // 미션 완료 처리
    @PostMapping("/{memberId}/missions/{missionId}/clear")
    public ApiResponse<MemberMissionResDto> clearMission(
            @PathVariable Long memberId,
            @PathVariable Long missionId
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                memberMissionService.clearMission(memberId, missionId)
        );
    }
}