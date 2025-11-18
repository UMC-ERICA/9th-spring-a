package com.example.jerry.domain.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MissionCreateReqDto {

    private String mission;       // 미션 내용
    private String missionDate;   // 작성일자 (yyyy-MM-dd)
    private Integer getPoints;    // 보상 포인트
    private Integer restaurantId; // 어느 가게에 추가할지
}
