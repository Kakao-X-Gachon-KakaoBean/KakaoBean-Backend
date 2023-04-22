package com.kakaobean.core.survey.exception;

import lombok.Getter;

@Getter
public class SameQuestionNumberException extends SurveyException{

    private static final String message = "중복되는 질문 번호가 있습니다.";
    private static final String errorCode = "S003";
    private static final Integer status = 400;

    public SameQuestionNumberException() {
        super(message, status, errorCode);
    }
}
