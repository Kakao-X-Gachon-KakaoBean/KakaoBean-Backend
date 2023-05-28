package com.kakaobean.core.survey.exception;

import lombok.Getter;

@Getter
public class ClosedSurveyException extends SurveyException{

    private static final String message = "참여 마감된 설문입니다.";
    private static final String errorCode = "S008";
    private static final Integer status = 400;

    public ClosedSurveyException() {
        super(message, status, errorCode);
    }

}
