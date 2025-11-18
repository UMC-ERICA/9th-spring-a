package com.example.jerry.domain.exception.code;


import com.example.jerry.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404_1", "해당 미션을 찾을 수 없습니다."),
    REGION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404_2", "해당 지역에 미션이 존재하지 않습니다."),
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "MISSION400_1", "잘못된 미션 요청입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}