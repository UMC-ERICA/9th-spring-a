package umc.server.domain.member.service;

import umc.server.domain.member.entity.Member;

public interface MemberService {

    public void createMember(Long memberId, String name, String email, String tel);
    public Member getMember(Long memberId);
}
