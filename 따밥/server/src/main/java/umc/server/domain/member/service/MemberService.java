package umc.server.domain.member.service;

import org.springframework.stereotype.Service;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.repository.MemberRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Optional<Member> findByUsername(String username) {

        return memberRepository.findMemberById(1L);
    }
}
