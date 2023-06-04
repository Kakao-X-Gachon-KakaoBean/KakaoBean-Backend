package com.kakaobean.core.factory.response;


import com.kakaobean.core.response.application.dto.request.RegisterSurveyResponseRequestDto;
import com.kakaobean.core.survey.domain.Survey;

import static com.kakaobean.core.factory.response.questionResponse.RegisterQuestionResponseRequestListDtoFactory.*;

public class RegisterSurveyResponseServiceDtoFactory {

    private RegisterSurveyResponseServiceDtoFactory(){}

    // 설문 응답 생성 성공 case1
    public static RegisterSurveyResponseRequestDto createSuccessSurveyResponseCase1Request(Long surveyId){
        return new RegisterSurveyResponseRequestDto(1L, surveyId, createSuccessListRequest());
    }

    // 설문 결과 통계를 위한 응답 생성 성공 case2
    public static RegisterSurveyResponseRequestDto createSuccessSurveyResponseCase2Request(Survey survey){
        return new RegisterSurveyResponseRequestDto(survey.getSurveyOwner().getMemberId(), survey.getId(), createSuccessListRequestForStatistics(survey));
    }

}
