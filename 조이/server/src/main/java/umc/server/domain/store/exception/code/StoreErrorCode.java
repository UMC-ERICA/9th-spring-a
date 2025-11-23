package umc.server.domain.store.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.server.global.apiPaylaod.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum StoreErrorCode implements BaseErrorCode {
    FOUND(HttpStatus.NOT_FOUND,
            "STORE404_1",
            "해당 가게를 찾지 못했습니다");
    private final HttpStatus status;
    private final String message;
    private final String code;
}
