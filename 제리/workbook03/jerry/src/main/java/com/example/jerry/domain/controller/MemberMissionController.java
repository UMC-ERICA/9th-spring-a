package com.example.jerry.domain.controller;

import com.example.jerry.domain.dto.request.MissionChallengeReqDto;
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
     * 진행중/완료된 미션 조회 7주차 피드백 수정
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

    @PostMapping("/challenge")
    public ApiResponse<MemberMissionResDto> challengeMission(
            @RequestBody MissionChallengeReqDto req
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                memberMissionService.challengeMission(req)
        );
    }

    /**
     * 미션 완료 처리
     * 7주차 피드백대로 PATCH로 수정!
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
