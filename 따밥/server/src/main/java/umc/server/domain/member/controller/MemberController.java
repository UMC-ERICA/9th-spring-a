package umc.server.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.member.converter.MemberConverter;
import umc.server.domain.member.dto.req.MemberReqDTO;
import umc.server.domain.member.dto.res.MemberResDTO;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.service.MemberService;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.GeneralSuccessCode;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/login")
    public ApiResponse<MemberResDTO.LoginDTO> login(
            @RequestParam Long memberId
    ){
        Optional<Member> member = memberService.findByUsername(memberId);
        GeneralSuccessCode code = GeneralSuccessCode._OK;
        return ApiResponse.success(
            code, MemberConverter.toLoginDTO(member)
        );
    }

    @PostMapping("/join")
    public ApiResponse<Void> join(
        @RequestBody MemberReqDTO.JoinDTO request
    ){
        memberService.join(request);
        GeneralSuccessCode code = GeneralSuccessCode._CREATED;

        return ApiResponse.success(
            code, null
        );
    }
}
