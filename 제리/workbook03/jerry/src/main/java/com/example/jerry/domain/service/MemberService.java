package com.example.jerry.domain.service;

import java.util.List;
import java.util.ArrayList;

import com.example.jerry.domain.converter.MemberConverter;
import com.example.jerry.domain.entity.Food;
import com.example.jerry.domain.entity.Member;
import com.example.jerry.domain.entity.MemberPreference;
import com.example.jerry.domain.dto.request.MemberLoginReqDto;
import com.example.jerry.domain.dto.request.MemberSignupReqDto ;
import com.example.jerry.domain.dto.response.MemberResDto;
import com.example.jerry.domain.exception.code.MemberErrorCode;
import com.example.jerry.domain.exception.code.FoodErrorCode;
import com.example.jerry.domain.exception.TestException;
import com.example.jerry.domain.repository.FoodRepository;
import com.example.jerry.domain.repository.MemberRepository;
import com.example.jerry.domain.repository.MemberPreferenceRepository;
import com.example.jerry.global.auth.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional

public class MemberService {

    private final MemberRepository memberRepository;
    private final FoodRepository foodRepository;
    private final MemberPreferenceRepository memberPreferenceRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입

    public MemberResDto signup(MemberSignupReqDto req) {

        if (memberRepository.findByEmail(req.getEmail()).isPresent()) {
            throw new TestException(MemberErrorCode.DUPLICATE_EMAIL);
        }

        if (req.getPhone() != null && memberRepository.existsByPhone(req.getPhone())) {
            throw new TestException(MemberErrorCode.DUPLICATE_PHONE);
        }

        String encodedPassword = passwordEncoder.encode(req.getPassword());

        // Member 생성 및 저장
        Member member = MemberConverter.toMember(
                req,
                encodedPassword,
                Role.ROLE_USER  // 기본 권한
        );

        memberRepository.save(member);

        // 선호 음식 저장
        if (req.getPreferCategory() != null && !req.getPreferCategory().isEmpty()) {

            List<MemberPreference> preferences = new ArrayList<>();

            for (Integer categoryId : req.getPreferCategory()) {

                // Food 존재 여부 검증
                Food food = foodRepository.findById(categoryId)
                        .orElseThrow(() -> new TestException(FoodErrorCode.FOOD_NOT_FOUND));

                // MemberPreference 생성
                MemberPreference preference = new MemberPreference(member, food);

                preferences.add(preference);
            }

            // 저장
            memberPreferenceRepository.saveAll(preferences);
        }

        return MemberResDto.from(member);
    }


    // 로그인
    public MemberResDto login(MemberLoginReqDto req) {

        Member member = memberRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new TestException(MemberErrorCode.INVALID_LOGIN));

        if (!passwordEncoder.matches(req.getPassword(), member.getPassword())) {
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