package com.kakaobean.core.survey.exception;

public class RangeQuestionBoundaryValueException extends SurveyException {

    private static final String message = "Range 질문의 최솟값과 최댓값이 동일합니다.";
    private static final String errorCode = "S006";
    private static final Integer status = 400;

    public RangeQuestionBoundaryValueException() {
        super(message, status, errorCode);
    }
}