package com.example.jerry.domain.exception.code;

import com.example.jerry.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum RestaurantErrorCode implements BaseErrorCode {

    RESTAURANT_NOT_FOUND(HttpStatus.NOT_FOUND, "RESTAURANT404_1", "해당 레스토랑을 찾을 수 없습니다."),
    DUPLICATE_RESTAURANT(HttpStatus.BAD_REQUEST, "RESTAURANT400_1", "이미 존재하는 레스토랑입니다."),
    INVALID_CATEGORY(HttpStatus.BAD_REQUEST, "RESTAURANT400_2", "유효하지 않은 음식 카테고리입니다."),
    INVALID_ADDRESS(HttpStatus.BAD_REQUEST, "RESTAURANT400_3", "유효하지 않은 주소입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}