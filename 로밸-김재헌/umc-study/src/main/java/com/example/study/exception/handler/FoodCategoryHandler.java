package com.example.study.exception.handler;

import com.example.study.apiPayload.code.BaseErrorCode;
import com.example.study.exception.GeneralException;

public class FoodCategoryHandler extends GeneralException {
    public FoodCategoryHandler(BaseErrorCode code) {
        super(code);
    }
}
