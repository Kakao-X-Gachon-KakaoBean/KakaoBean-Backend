package com.kakaobean.core.survey.exception;

import lombok.Getter;

@Getter
public class NoMatchingQuestionNumberException extends SurveyException{

    private static final String message = "번 질문에 해당하는 번호가 없습니다.";
    private static final String errorCode = "S001";
    private static final Integer status = 400;

    public NoMatchingQuestionNumberException(String nextQuestionNumber) {
        super(nextQuestionNumber + message, status, errorCode);
    }
}
