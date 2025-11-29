package com.example.jerry.domain.exception.code;

import com.example.jerry.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum PagingErrorCode implements BaseErrorCode {

    INVALID_PAGE(HttpStatus.BAD_REQUEST, "PAGE400_1", "page 파라미터는 1 이상의 정수여야 합니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
