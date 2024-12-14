package com.example.study.exception.handler;

import com.example.study.apiPayload.code.BaseErrorCode;
import com.example.study.exception.GeneralException;

public class MissionHandler extends GeneralException {
    public MissionHandler(BaseErrorCode code) {
        super(code);
    }
}
