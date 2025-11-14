package umc.server.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.member.dto.res.MemberResDTO;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.service.MemberService;
import umc.server.global.apiPaylaod.ApiResponse;
import umc.server.global.apiPaylaod.code.GeneralSuccessCode;


    @RestController
    @RequiredArgsConstructor
    @RequestMapping("/members")
    public class MemberController {

        private final MemberService memberService;

        // 회원 조회
        @GetMapping("/{memberId}")
        public ApiResponse getMember(@PathVariable Long memberId) {
            Member member = memberService.getMember(memberId);

            // Member -> DTO 변환
            MemberResDTO.MemberRes response = MemberResDTO.MemberRes.builder()
                    .memberId(member.getId())
                    .name(member.getName())
                    .email(member.getEmail())
                    .tel(member.getTel())
                    .build();

            return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
        }

        // 회원 생성
        @PostMapping
        public ApiResponse createMember(
                @RequestParam Long memberId,
                @RequestParam String name,
                @RequestParam String email,
                @RequestParam String tel
        ) {
            memberService.createMember(memberId, name, email, tel);
            return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, "회원 생성 성공!");
        }

    }
