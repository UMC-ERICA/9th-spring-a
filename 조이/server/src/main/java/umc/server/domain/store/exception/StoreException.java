package umc.server.domain.store.exception;

import umc.server.global.apiPaylaod.code.BaseErrorCode;
import umc.server.global.apiPaylaod.exception.GeneralException;

public class StoreException extends GeneralException {
    public StoreException(BaseErrorCode code)
    {super(code);}
}
