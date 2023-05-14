package com.kakaobean.core.factory.response;


import com.kakaobean.core.response.application.dto.request.RegisterSurveyResponseRequestDto;

import static com.kakaobean.core.factory.response.questionResponse.RegisterQuestionResponseRequestListDtoFactory.*;

public class RegisterSurveyResponseServiceDtoFactory {

    private RegisterSurveyResponseServiceDtoFactory(){}

    // 설문 응답 생성 성공 case1
    public static RegisterSurveyResponseRequestDto createSuccessCase1Request(){
        return new RegisterSurveyResponseRequestDto(1L, 2L, createSuccessListRequest());
    }

}
