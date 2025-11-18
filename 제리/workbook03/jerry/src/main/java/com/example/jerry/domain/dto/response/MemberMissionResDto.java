package com.example.jerry.domain.dto.response;

import com.example.jerry.domain.entity.MemberMission;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberMissionResDto {

    private Integer memberMissionId;
    private Boolean clear;
    private Long memberId;
    private Long missionId;
    private String mission;
    private String missionDate;

    public static MemberMissionResDto from(MemberMission mm) {
        return MemberMissionResDto.builder()
                .memberMissionId(mm.getUserMissionId())
                .clear(mm.getClear())
                .memberId(mm.getMember().getMemberId())
                .missionId(mm.getMission().getMissionId())
                .mission(mm.getMission().getMission())
                .missionDate(mm.getMission().getMissionDate().toString())
                .build();
    }
}
