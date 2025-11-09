package umc.server.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode {
    _OK(HttpStatus.OK,
            "COMMON200",
            "성공적으로 처리되었습니다."),
    _CREATED(HttpStatus.CREATED,
            "COMMON201",
            "리소스가 성공적으로 생성되었습니다."),
    _ACCEPTED(HttpStatus.ACCEPTED,
            "COMMON202",
            "요청이 접수되었습니다."),
    _ALREADY_REPORTED(HttpStatus.ALREADY_REPORTED,
            "COMMON208",
            "이미 처리된 요청입니다."),
    _NO_CONTENT(HttpStatus.NO_CONTENT,
            "COMMON204",
            "성공적으로 처리되었고, 반환할 내용이 없습니다."),
    ;
    private final HttpStatus status;
    private final String code;
    private final String message;

}
