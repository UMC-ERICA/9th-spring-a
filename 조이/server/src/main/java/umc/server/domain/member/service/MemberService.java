package umc.server.domain.member.service;

import umc.server.domain.member.dto.req.MemberReqDTO;
import umc.server.domain.member.dto.res.MemberResDTO;
import umc.server.domain.member.entity.Member;

public interface MemberService {

    //회원가입
    MemberResDTO.JoinDTO signup(
            MemberReqDTO.JoinDTO dto
    );
    MemberResDTO.LoginDTO login(
            MemberReqDTO.LoginDTO dto
    );
}
