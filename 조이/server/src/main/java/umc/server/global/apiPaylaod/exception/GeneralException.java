package umc.server.global.apiPaylaod.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import umc.server.global.apiPaylaod.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {

    private final BaseErrorCode code;
}