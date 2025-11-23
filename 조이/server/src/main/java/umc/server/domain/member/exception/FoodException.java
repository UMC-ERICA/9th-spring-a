package umc.server.domain.member.exception;

import umc.server.global.apiPaylaod.code.BaseErrorCode;
import umc.server.global.apiPaylaod.exception.GeneralException;

public class FoodException extends GeneralException {
    public FoodException(BaseErrorCode code) { super(code);}
}
