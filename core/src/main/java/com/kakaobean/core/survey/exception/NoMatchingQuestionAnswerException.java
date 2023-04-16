package com.kakaobean.core.survey.exception;

import lombok.Getter;

@Getter
public class NoMatchingQuestionAnswerException extends SurveyException{

    private static final String message = "' 내용의 답변이 없습니다.";
    private static final String errorCode = "S002";
    private static final Integer status = 400;

    public NoMatchingQuestionAnswerException(String answer) {
        super("'" + answer + message, status, errorCode);
    }
}
