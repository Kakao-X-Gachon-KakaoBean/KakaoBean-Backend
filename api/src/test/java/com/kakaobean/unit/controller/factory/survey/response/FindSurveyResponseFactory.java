package com.kakaobean.unit.controller.factory.survey.response;

import com.kakaobean.core.survey.application.dto.response.FindSurveyResponseDto;

import static com.kakaobean.unit.controller.factory.survey.response.question.FindQuestionResponseListFactory.*;

public class FindSurveyResponseFactory {

    public static FindSurveyResponseDto create(){
        return new FindSurveyResponseDto(1L, "title", createSuccessListRequest());
    }
}
