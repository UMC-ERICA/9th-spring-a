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

    /**
     * 진행중/완료된 미션 조회 - 통합 API
     * /member-missions/{memberId}?status=uncleared
     * /member-missions/{memberId}?status=cleared
     */
    @GetMapping("/{memberId}")
    public ApiResponse<?> getMemberMissions(
            @PathVariable Long memberId,
            @RequestParam(defaultValue = "uncleared") String status,
            Pageable pageable
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                memberMissionService.getMissionsByStatus(memberId, status, pageable)
        );
    }

    /**
     * 미션 완료 처리
     * PATCH 사용 권장
     */
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
}
