package com.example.jerry.domain.exception;

import com.example.jerry.global.apiPayload.code.BaseErrorCode;
import com.example.jerry.global.apiPayload.exception.GeneralException;

public class TestException extends GeneralException {
    public TestException(BaseErrorCode code) {
        super(code);
    }
}