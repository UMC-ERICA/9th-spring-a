package com.example.jerry.domain.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MissionReqDto {

    private Long memberId;   // 요청한 사용자 ID
    private String region;   // 지역명
    private int page;        // 페이지 번호
    private int size;        // 페이지 크기
}