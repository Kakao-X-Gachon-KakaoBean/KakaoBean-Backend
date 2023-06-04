package com.kakaobean.core.survey.exception;

import lombok.Getter;

@Getter
public class NotExistsSurveyException extends SurveyException {

    private static final String message = "존재하지 않는 설문입니다.";
    private static final String errorCode = "S007";
    private static final Integer status = 400;

    public NotExistsSurveyException() {
        super(message, status, errorCode);
    }

}