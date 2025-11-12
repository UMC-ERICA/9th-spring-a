package umc.server.domain.member.service;

import org.springframework.stereotype.Service;
import umc.server.domain.member.dto.req.MemberReqDTO;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.exception.MemberErrorCode;
import umc.server.domain.member.exception.MemberException;
import umc.server.domain.member.repository.MemberRepository;

import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Optional<Member> findByUsername(Long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        if (member.isEmpty()) throw new MemberException(MemberErrorCode.MEMBER_NOT_FOUND);
        return member;
    }

    @Override
    public void join(MemberReqDTO.JoinDTO request) {
        validateRequest(request);

        Member member = Member.builder()
                .email(request.getEmail())
                .nickname(request.getName())
                .gender(request.getGender())
                .birth(request.getBirthday())
                .photo(request.getMemberPhoto())
                .phoneNumber(request.getPhoneNumber())
                .build();

        memberRepository.save(member);
    }

    private void validateRequest(MemberReqDTO.JoinDTO request) {
        if (request.getName() == null)
            throw new MemberException(MemberErrorCode.NICKNAME_NOT_EXIST);
        if (memberRepository.existsByPhoneNumber(request.getPhoneNumber()))
            throw new MemberException(MemberErrorCode.DUPLICATE_PHONE_NUMBER);
        if (memberRepository.existsByEmail(request.getEmail()))
            throw new MemberException(MemberErrorCode.DUPLICATE_EMAIL);
    }

}
