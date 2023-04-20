package com.kakaobean.core.factory.survey;

import com.kakaobean.core.survey.application.dto.RegisterSurveyRequestDto;

import static com.kakaobean.core.factory.survey.question.RegisterQuestionRequestListDtoFactory.*;

public class RegisterSurveyServiceDtoFactory {

    private RegisterSurveyServiceDtoFactory() {}

    public static RegisterSurveyRequestDto createSuccessCase1Request(){
        return new RegisterSurveyRequestDto(1L, createSuccessListRequest());
    }

    public static RegisterSurveyRequestDto createFailCase1Request(){
        return new RegisterSurveyRequestDto(1L, createFailListCase1Request());
    }

    public static RegisterSurveyRequestDto createFailCase2Request(){
        return new RegisterSurveyRequestDto(1L, createFailListCase2Request());
    }

    public static RegisterSurveyRequestDto createFailCase3Request() {
        return new RegisterSurveyRequestDto(1L, createFailListCase3Request());
    }
}
