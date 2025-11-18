package umc.server.domain.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.member.converter.MemberConverter;
import umc.server.domain.member.dto.req.MemberReqDTO;
import umc.server.domain.member.dto.res.MemberResDTO;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.exception.MemberSuccessCode;
import umc.server.domain.member.service.MemberService;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.GeneralSuccessCode;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final MemberService memberService;

    @GetMapping("/login")
    public ApiResponse<MemberResDTO.LoginDTO> login(
            @RequestParam Long memberId
    ){
        Member member = memberService.findByUsername(memberId);
        GeneralSuccessCode code = GeneralSuccessCode._OK;
        return ApiResponse.success(
                code, MemberConverter.toLoginDTO(member)
        );
    }

    @PostMapping("/users")
    public ApiResponse<MemberResDTO.JoinDTO> join(
            @RequestBody @Valid MemberReqDTO.JoinDTO request
    ){
        return ApiResponse.success(
                MemberSuccessCode.MEMBER_FOUND, memberService.join(request)
        );
    }

}