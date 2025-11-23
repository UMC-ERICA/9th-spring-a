package umc.server.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.member.dto.req.MemberReqDTO;
import umc.server.domain.member.dto.res.MemberResDTO;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.exception.code.MemberSuccessCode;
import umc.server.domain.member.service.MemberService;
import umc.server.global.apiPaylaod.ApiResponse;
import umc.server.global.apiPaylaod.code.GeneralSuccessCode;


    @RestController
    @RequiredArgsConstructor
    @RequestMapping("/members")
    public class MemberController {

        private final MemberService memberService;

        // 회원가입
        @PostMapping("/sign-up")
        public ApiResponse<MemberResDTO.JoinDTO> signUp(
                @RequestBody MemberReqDTO.JoinDTO dto
        ){
            return ApiResponse.onSuccess(MemberSuccessCode.FOUND,memberService.signup(dto));
        }

        // 회원 로그인
        @GetMapping("/{memberId}/login")
        public ApiResponse<MemberResDTO.LoginDTO> login(
                @RequestBody MemberReqDTO.LoginDTO dto) {
            return ApiResponse.onSuccess(MemberSuccessCode.FOUND, memberService.login(dto));


        }

    }
