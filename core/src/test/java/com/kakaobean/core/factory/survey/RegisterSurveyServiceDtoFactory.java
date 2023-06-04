package com.kakaobean.core.factory.survey;

import com.kakaobean.core.survey.application.dto.request.RegisterSurveyRequestDto;

import static com.kakaobean.core.factory.survey.question.RegisterQuestionRequestListDtoFactory.*;

public class RegisterSurveyServiceDtoFactory {

    private RegisterSurveyServiceDtoFactory() {}

    public static RegisterSurveyRequestDto createSuccessCase1Request(){
        return new RegisterSurveyRequestDto("title", 1L, createSuccessListRequest());
    }

    public static RegisterSurveyRequestDto createSuccessCase1Request(Long memberId){
        return new RegisterSurveyRequestDto("title", memberId, createSuccessListRequest());
    }

    // 분기점에서 다음 질문 번호를 없는 것으로 넣었다
    public static RegisterSurveyRequestDto createFailCase1Request(){
        return new RegisterSurveyRequestDto("title", 1L, createFailListCase1Request());
    }

    //분기점에서 조건으로 없는 답변을 넣었다
    public static RegisterSurveyRequestDto createFailCase2Request(){
        return new RegisterSurveyRequestDto("title", 1L, createFailListCase2Request());
    }

    //분기점에서 다음 질문으로 없는 질문 번호를 넣었다
    public static RegisterSurveyRequestDto createFailCase3Request() {
        return new RegisterSurveyRequestDto("title", 1L, createFailListCase3Request());
    }

    //중복되는 질문 번호가 있다.
    public static RegisterSurveyRequestDto createFailCase4Request() {
        return new RegisterSurveyRequestDto("title", 1L, createFailListCase4Request());
    }

    //"Range 질문의 min과 max 범위를 동일하 설정하였다.
    public static RegisterSurveyRequestDto createFailCase5Request() {
        return new RegisterSurveyRequestDto("title", 1L, createFailListCase5Request());
    }

    //분기문에서 동일한 조건을 가진 로직을 2개 이상 포함하였다.
    public static RegisterSurveyRequestDto createFailCase6Request() {
        return new RegisterSurveyRequestDto("title", 1L, createFailListCase6Request());
    }
}
