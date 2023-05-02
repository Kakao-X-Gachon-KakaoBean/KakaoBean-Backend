package com.kakaobean.unit.controller.factory.survey.response;

import com.kakaobean.core.survey.application.dto.response.FindSurveyResponseDto;
import com.kakaobean.unit.controller.factory.survey.response.question.FindQuestionResponseFactory;
import com.kakaobean.unit.controller.factory.survey.response.question.FindQuestionResponseListFactory;

import java.util.List;

import static com.kakaobean.unit.controller.factory.survey.response.question.FindQuestionResponseListFactory.*;

public class FindSurveyResponseFactory {

    public static FindSurveyResponseDto create(){
        return new FindSurveyResponseDto(1L, "title", createSuccessListRequest());
    }
}
