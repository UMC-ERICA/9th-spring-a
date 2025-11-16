package umc.server.domain.store.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.server.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum StoreErrorCode implements BaseErrorCode {
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND,
            "STORE404_1",
            "일치하는 가게가 없습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
