package umc.server.domain.test.exception;

import umc.server.global.apiPayload.code.BaseErrorCode;
import umc.server.global.apiPayload.exception.GeneralException;

public class TestException extends GeneralException {

    //  BaseErrorCode를 받아서 부모에게 전달.
    public TestException(BaseErrorCode code) {
        super(code);
    }
}
