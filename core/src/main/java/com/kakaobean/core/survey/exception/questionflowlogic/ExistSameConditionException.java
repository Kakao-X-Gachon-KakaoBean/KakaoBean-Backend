package com.kakaobean.core.survey.exception.questionflowlogic;

import com.kakaobean.core.survey.exception.SurveyException;

public class ExistSameConditionException extends SurveyException {

    private static final String message = "동일한 조건을 가진 로직이 2개 이상 존재합니다.";
    private static final String errorCode = "S005";
    private static final Integer status = 400;

    public ExistSameConditionException() {
        super(message, status, errorCode);
    }
}
