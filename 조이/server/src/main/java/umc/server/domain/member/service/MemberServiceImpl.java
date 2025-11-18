package umc.server.domain.member.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public void createMember(Long memberId, String name, String email, String tel) {
        Member member = Member.builder()
                .id(memberId)       // PK
                .name(name)
                .email(email)
                .tel(tel)
                .build();

        memberRepository.save(member);


    }



    @Override
    @Transactional
    public Member getMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("회원이 존재하지 않습니다"));
    }
}