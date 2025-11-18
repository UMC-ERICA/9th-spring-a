package com.example.jerry.domain.exception.code;

import com.example.jerry.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW400_1", "존재하지 않는 회원입니다."),
    RESTAURANT_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW400_2", "존재하지 않는 가게입니다."),
    INVALID_RATING(HttpStatus.BAD_REQUEST, "REVIEW400_3", "별점은 1~5 사이여야 합니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}