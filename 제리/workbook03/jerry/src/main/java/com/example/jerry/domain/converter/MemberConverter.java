package com.example.jerry.domain.converter;

import com.example.jerry.domain.dto.request.MemberSignupReqDto;
import com.example.jerry.domain.dto.response.MemberResDto;
import com.example.jerry.domain.entity.Member;
import com.example.jerry.global.auth.enums.Role;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberConverter {

    public static Member toMember(MemberSignupReqDto reqDto,
                                  String encodedPassword,
                                  Role role) {
        if (reqDto == null) {
            return null;
        }

        return Member.builder()
                .email(reqDto.getEmail())
                .password(encodedPassword)
                .name(reqDto.getName())
                .phone(reqDto.getPhone())
                .role(role)
                .build();
    }

    public static MemberResDto toMemberResDto(Member member) {
        return MemberResDto.builder()
                .memberId(member.getMemberId())
                .email(member.getEmail())
                .name(member.getName())
                .phone(member.getPhone())
                .points(member.getPoints())
                .build();
    }
}
