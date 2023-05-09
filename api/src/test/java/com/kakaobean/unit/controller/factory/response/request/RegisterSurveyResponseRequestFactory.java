package com.kakaobean.unit.controller.factory.response.request;

import com.kakaobean.response.dto.request.RegisterSurveyResponseRequest;

import static com.kakaobean.unit.controller.factory.response.request.question.RegisterQuestionResponseRequestListFactory.*;

public class RegisterSurveyResponseRequestFactory {

    private RegisterSurveyResponseRequestFactory(){}

    public static RegisterSurveyResponseRequest createSuccessSurveyResponseRequest() {
        return new RegisterSurveyResponseRequest(1L, createSuccessQuestionResponseRequestList());
    }
}
