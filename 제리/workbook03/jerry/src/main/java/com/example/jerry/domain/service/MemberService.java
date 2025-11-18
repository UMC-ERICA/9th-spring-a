package com.example.jerry.domain.service;

import com.example.jerry.domain.entity.Member;
import com.example.jerry.domain.dto.request.MemberReqDto;
import com.example.jerry.domain.dto.response.MemberResDto;
import com.example.jerry.domain.exception.code.MemberErrorCode;
import com.example.jerry.domain.exception.TestException;
import com.example.jerry.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원가입
    public MemberResDto signup(MemberReqDto req) {

        if (memberRepository.findByEmail(req.getEmail()).isPresent()) {
            throw new TestException(MemberErrorCode.DUPLICATE_EMAIL);
        }

        if (req.getPhone() != null && memberRepository.existsByPhone(req.getPhone())) {
            throw new TestException(MemberErrorCode.DUPLICATE_PHONE);
        }

        Member member = Member.builder()
                .email(req.getEmail())
                .password(req.getPassword())
                .name(req.getName())
                .phone(req.getPhone())
                .build();

        memberRepository.save(member);

        return MemberResDto.from(member);
    }

    // 로그인
    public MemberResDto login(MemberReqDto req) {

        Member member = memberRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new TestException(MemberErrorCode.INVALID_LOGIN));

        if (!member.getPassword().equals(req.getPassword())) {
            throw new TestException(MemberErrorCode.INVALID_LOGIN);
        }

        return MemberResDto.from(member);
    }

    // 멤버 단일 조회
    public MemberResDto getMember(Long memberId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new TestException(MemberErrorCode.MEMBER_NOT_FOUND));

        return MemberResDto.from(member);
    }
}