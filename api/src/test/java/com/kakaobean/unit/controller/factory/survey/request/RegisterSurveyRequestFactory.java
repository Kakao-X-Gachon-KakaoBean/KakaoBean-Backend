package com.kakaobean.unit.controller.factory.survey.request;

import com.kakaobean.survey.dto.request.RegisterSurveyRequest;

import static com.kakaobean.unit.controller.factory.survey.request.question.RegisterQuestionRequestListFactory.*;

public class RegisterSurveyRequestFactory {

    private RegisterSurveyRequestFactory(){}

    public static RegisterSurveyRequest createSuccessCase1Request(){
        return new RegisterSurveyRequest("survey title", createSuccessListRequest());
    }
}
