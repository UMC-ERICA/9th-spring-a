package com.example.jerry.domain.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberMissionReqDto {

    private Long memberId;
    private Long missionId;
}