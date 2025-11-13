package umc.server.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.member.dto.req.MemberReqDTO;
import umc.server.domain.member.dto.res.MemberResDTO;
import umc.server.domain.member.service.MemberService;
import umc.server.domain.mission.entity.mapping.MemberMission;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
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

    @GetMapping("/{memberId}/reviews")
    public ApiResponse<MemberResDTO.MyReviewsDTO> getMyReviews(
            @PathVariable Long memberId
    ){
        MemberResDTO.MyReviewsDTO response = memberService.getMyReviews(memberId);
        GeneralSuccessCode code = GeneralSuccessCode._OK;
        return ApiResponse.success(code, response);
    }
}
