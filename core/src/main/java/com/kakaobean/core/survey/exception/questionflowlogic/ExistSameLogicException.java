package com.kakaobean.core.survey.exception.questionflowlogic;

import com.kakaobean.core.survey.exception.SurveyException;

public class ExistSameLogicException extends SurveyException {

    private static final String message = "동일한 로직이 존재합니다.";
    private static final String errorCode = "S004";
    private static final Integer status = 400;

    public ExistSameLogicException() {
        super(message, status, errorCode);
    }
}
