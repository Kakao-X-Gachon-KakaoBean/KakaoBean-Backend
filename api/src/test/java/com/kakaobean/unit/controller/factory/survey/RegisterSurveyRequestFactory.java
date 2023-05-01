package com.kakaobean.unit.controller.factory.survey;

import com.kakaobean.survey.dto.request.RegisterSurveyRequest;
import com.kakaobean.survey.dto.request.question.RegisterQuestionRequest;

import java.util.List;

import static com.kakaobean.unit.controller.factory.survey.question.RegisterQuestionRequestListFactory.*;

public class RegisterSurveyRequestFactory {

    private RegisterSurveyRequestFactory(){}

    public static RegisterSurveyRequest createSuccessCase1Request(){
        return new RegisterSurveyRequest("survey title", createSuccessListRequest());
    }
}
