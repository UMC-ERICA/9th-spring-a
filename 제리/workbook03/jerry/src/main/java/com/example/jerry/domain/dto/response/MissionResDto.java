package com.example.jerry.domain.dto.response;

import com.example.jerry.domain.entity.Mission;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class MissionResDto {

    private Long missionId;
    private String mission;
    private LocalDate missionDate;
    private Integer getPoints;

    private Integer restaurantId;
    private String restaurantName;
    private String restaurantAddress;

    public static MissionResDto from(Mission mission) {
        return MissionResDto.builder()
                .missionId(mission.getMissionId())
                .mission(mission.getMission())
                .missionDate(mission.getMissionDate())
                .getPoints(mission.getGetPoints())
                .restaurantId(mission.getRestaurant().getRestaurantsId())
                .restaurantName(mission.getRestaurant().getName())
                .restaurantAddress(mission.getRestaurant().getAddress().getAddress())
                .build();
    }
}