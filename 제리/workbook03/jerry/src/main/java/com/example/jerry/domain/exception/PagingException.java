package com.example.jerry.domain.exception;

import com.example.jerry.global.apiPayload.code.BaseErrorCode;
import com.example.jerry.global.apiPayload.exception.GeneralException;

public class PagingException extends GeneralException {

    public PagingException(BaseErrorCode code) {
        super(code);
    }
}
