package umc.server.domain.member.exception;

import umc.server.global.apiPaylaod.code.BaseErrorCode;
import umc.server.global.apiPaylaod.exception.GeneralException;

public class MemberException extends GeneralException {
    public MemberException(BaseErrorCode code) {
        super(code);
    }
}

