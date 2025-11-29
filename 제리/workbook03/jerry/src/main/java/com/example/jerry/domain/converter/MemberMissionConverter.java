package com.example.jerry.domain.converter;

import com.example.jerry.domain.dto.response.MemberMissionResDto;
import com.example.jerry.domain.entity.MemberMission;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberMissionConverter {

    public static MemberMissionResDto.MemberMissionPageDto toInProgressPage(Page<MemberMission> page) {
        return MemberMissionResDto.MemberMissionPageDto.builder()
                .missionList(
                        page.getContent()
                                .stream()
                                .map(MemberMissionResDto::from)
                                .toList()
                )
                .listSize(page.getNumberOfElements())
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }
}
