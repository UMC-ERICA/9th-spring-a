package umc.server.domain.review.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.server.global.apiPaylaod.code.BaseErrorCode;

@AllArgsConstructor
@Getter
public enum ReviewErrorCode implements BaseErrorCode {
    FOUND(HttpStatus.NOT_FOUND,
            "REVIEW404_1",
            "해당 리뷰를 찾지 못했습니다");
    private HttpStatus status;
    private String message;
    private String code;
}
