package com.example.study.exception.handler;

import com.example.study.apiPayload.code.BaseErrorCode;
import com.example.study.exception.GeneralException;

public class RegionHandler extends GeneralException {
    public RegionHandler(BaseErrorCode code) {
        super(code);
    }
}
