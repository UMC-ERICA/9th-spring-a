package umc.server.domain.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.member.dto.req.MemberReqDTO;
import umc.server.domain.member.dto.res.MemberResDTO;
import umc.server.domain.member.service.MemberService;
import umc.server.domain.mission.dto.MissionResDTO;
import umc.server.domain.mission.entity.mapping.MemberMission;
import umc.server.domain.review.dto.res.ReviewResDTO;
import umc.server.global.annotation.ProperPageSize;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/users")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/{memberId}/info")
    public ApiResponse<MemberResDTO.HomeTopDTO> getHomeTop(
            @PathVariable Long memberId,
            @ModelAttribute MemberReqDTO.HomeDTO homeDTO
    ){
        MemberResDTO.HomeTopDTO response = memberService.getHomeTop(memberId, homeDTO.getRegion(), homeDTO.getCursor(), homeDTO.getSize());
        GeneralSuccessCode code = GeneralSuccessCode._OK;

        return ApiResponse.success(code, response);
    }

    @PostMapping("/{memberId}/missions/{missionId}/challenge")
    public ApiResponse<MemberResDTO.ChallengeMissionDTO> challengeMission(
            @PathVariable Long memberId,
            @PathVariable Long missionId
    ){
        return ApiResponse.success(
                GeneralSuccessCode._OK,
                memberService.challengeMission(memberId, missionId)
        );
    }


    @GetMapping("/{memberId}/missions/challenge")
    public ApiResponse<MissionResDTO.MissionPreviewList> getMyChallengeMissions(
            @PathVariable Long memberId,
            @RequestParam(defaultValue = "0") @ProperPageSize Integer page
    ){
        return ApiResponse.success(
                GeneralSuccessCode._OK,
                memberService.getMyMissions(memberId, page)
        );
    }

    @PostMapping("/{memberId}/missions/{missionId}")
    public ApiResponse<Void> missionSuccess(
            @PathVariable Long memberId,
            @PathVariable Long missionId
    ){
        // MemberMission에서 isCompleted = true; 로 변경
        memberService.missionSuccess(memberId, missionId);
        GeneralSuccessCode code = GeneralSuccessCode._OK;

        return ApiResponse.success(code, null);
    }

    @GetMapping("/{memberId}/mypage")
    public ApiResponse<MemberResDTO.MyPageDTO> getMyPage(
            @PathVariable Long memberId
    ){
        MemberResDTO.MyPageDTO response = memberService.getMyPage(memberId);
        GeneralSuccessCode code = GeneralSuccessCode._OK;
        return ApiResponse.success(code, response);
    }


    // 페이징 처리로 변경
    @GetMapping("/{memberId}/reviews")
    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getMyReviews(
            @PathVariable Long memberId,
            @RequestParam(defaultValue = "0") @Valid @ProperPageSize Integer page
    ){

        return ApiResponse.success(
                GeneralSuccessCode._OK,
                memberService.getMyReviews(memberId, page)
        );
    }
}
