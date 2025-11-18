package com.example.jerry.domain.controller;

import com.example.jerry.domain.dto.request.MemberReqDto;
import com.example.jerry.domain.dto.response.MemberResDto;
import com.example.jerry.domain.service.MemberService;
import com.example.jerry.global.apiPayload.ApiResponse;
import com.example.jerry.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    @PostMapping("/signup")
    public ApiResponse<MemberResDto> signup(@RequestBody MemberReqDto req) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                memberService.signup(req)
        );
    }

    // 로그인
    @PostMapping("/login")
    public ApiResponse<MemberResDto> login(@RequestBody MemberReqDto req) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                memberService.login(req)
        );
    }

    // 회원 상세 조회
    @GetMapping("/{memberId}")
    public ApiResponse<MemberResDto> getMember(
            @PathVariable Long memberId
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                memberService.getMember(memberId)
        );
    }
}