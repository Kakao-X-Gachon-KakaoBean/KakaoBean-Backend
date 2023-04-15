package com.kakaobean.unit.controller.factory.survey;

import com.kakaobean.survey.dto.request.RegisterSurveyRequest;

import static com.kakaobean.unit.controller.factory.survey.question.RegisterQuestionRequestListFactory.*;

public class RegisterSurveyRequestFactory {

    private RegisterSurveyRequestFactory(){}

    public static RegisterSurveyRequest createSuccessCase1Request(){
        return new RegisterSurveyRequest(createListRequest());
    }
}
