package com.example.study.exception.handler;

import com.example.study.apiPayload.code.BaseErrorCode;
import com.example.study.exception.GeneralException;

public class MemberHandler extends GeneralException {
    public MemberHandler(BaseErrorCode code) {
        super(code);
    }
}
