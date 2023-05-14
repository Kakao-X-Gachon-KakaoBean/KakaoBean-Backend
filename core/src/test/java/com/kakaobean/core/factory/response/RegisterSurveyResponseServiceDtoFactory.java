package com.kakaobean.core.factory.response;


import com.kakaobean.core.response.application.dto.request.RegisterSurveyResponseRequestDto;

import static com.kakaobean.core.factory.response.questionResponse.RegisterQuestionResponseRequestListDtoFactory.*;

public class RegisterSurveyResponseServiceDtoFactory {

    private RegisterSurveyResponseServiceDtoFactory(){}

    public static RegisterSurveyResponseRequestDto createSuccessCase1Request(){
        return new RegisterSurveyResponseRequestDto(1L, 2L, createSuccessListRequest());
    }

}
