package com.example.study.exception.handler;

import com.example.study.apiPayload.code.BaseErrorCode;
import com.example.study.exception.GeneralException;

public class StoreHandler extends GeneralException {
    public StoreHandler(BaseErrorCode code) {
        super(code);
    }
}
