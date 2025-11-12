package umc.server.domain.member.service;

import umc.server.domain.member.dto.req.MemberReqDTO;
import umc.server.domain.member.entity.Member;

import java.util.Optional;

public interface MemberService {
    Optional<Member> findByUsername(Long memberId);
    void join(MemberReqDTO.JoinDTO request);
}
