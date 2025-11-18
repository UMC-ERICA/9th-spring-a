package com.example.jerry.domain.exception.code;

import com.example.jerry.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberMissionErrorCode implements BaseErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBERMISSION404_1", "해당 회원을 찾을 수 없습니다."),
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBERMISSION404_2", "해당 미션을 찾을 수 없습니다."),
    MEMBER_MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBERMISSION404_3", "회원의 미션 기록을 찾을 수 없습니다."),
    ALREADY_COMPLETED(HttpStatus.BAD_REQUEST, "MEMBERMISSION400_1", "이미 완료한 미션입니다."),
    INVALID_STATUS(HttpStatus.BAD_REQUEST, "MEMBERMISSION400_2", "올바르지 않은 상태값입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}