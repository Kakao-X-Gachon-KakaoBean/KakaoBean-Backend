package com.kakaobean.core.survey.application.dto.response;

import lombok.Getter;

@Getter
public class CloseSurveyResponseDto {

    private static final String SUCCESS_MESSAGE = "설문이 마감되었습니다!";

    private final String message;

    public CloseSurveyResponseDto() {
        this.message = SUCCESS_MESSAGE;
    }
}