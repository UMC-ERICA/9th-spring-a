package com.example.jerry.domain.dto.response;

import com.example.jerry.domain.entity.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberResDto {

    private Long memberId;
    private String email;
    private String name;
    private String phone;
    private Long points;

    public static MemberResDto from(Member member) {
        return MemberResDto.builder()
                .memberId(member.getMemberId())
                .email(member.getEmail())
                .name(member.getName())
                .phone(member.getPhone())
                .points(member.getPoints())
                .build();
    }
}