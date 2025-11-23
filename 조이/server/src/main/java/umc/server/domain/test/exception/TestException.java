package umc.server.domain.test.exception;

import umc.server.global.apiPaylaod.code.BaseErrorCode;
import umc.server.global.apiPaylaod.exception.GeneralException;

public class TestException extends GeneralException {
    public TestException(BaseErrorCode code) {
        super(code);
    }
}