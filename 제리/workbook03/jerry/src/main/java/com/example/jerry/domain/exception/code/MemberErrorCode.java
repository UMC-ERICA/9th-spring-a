package com.example.jerry.domain.exception.code;

import com.example.jerry.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404_1", "해당 회원을 찾을 수 없습니다."),
    DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST, "MEMBER400_1", "이미 사용 중인 이메일입니다."),
    DUPLICATE_PHONE(HttpStatus.BAD_REQUEST, "MEMBER400_2", "이미 등록된 전화번호입니다."),
    INVALID_LOGIN(HttpStatus.UNAUTHORIZED, "MEMBER401_1", "이메일 또는 비밀번호가 잘못되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}