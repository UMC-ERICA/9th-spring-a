package umc.server.domain.review.exception;

import umc.server.global.apiPaylaod.code.BaseErrorCode;
import umc.server.global.apiPaylaod.exception.GeneralException;

import java.time.LocalDate;

public class ReviewException extends GeneralException {
    public ReviewException(BaseErrorCode code) {
        super(code);
    }

}
