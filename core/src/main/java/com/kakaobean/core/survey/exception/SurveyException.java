package com.kakaobean.core.survey.exception;

import com.kakaobean.core.common.ApplicationException;
import lombok.Getter;

@Getter
public abstract class SurveyException extends ApplicationException {

    public SurveyException(String message, Integer status, String errorCode) {
        super(message, status, errorCode);
    }
}
