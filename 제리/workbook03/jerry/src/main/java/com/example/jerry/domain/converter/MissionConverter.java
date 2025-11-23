package com.example.jerry.domain.converter;

import com.example.jerry.domain.dto.response.MissionResDto;
import com.example.jerry.domain.entity.Mission;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MissionConverter {

    public static MissionResDto.MissionPageDto toRestaurantMissionPage(Page<Mission> page) {
        return MissionResDto.MissionPageDto.builder()
                .missionList(
                        page.getContent()
                                .stream()
                                .map(MissionResDto::from)
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
