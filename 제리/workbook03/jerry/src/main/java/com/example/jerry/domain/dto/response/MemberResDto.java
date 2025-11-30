package com.example.jerry.domain.dto.response;

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
}
