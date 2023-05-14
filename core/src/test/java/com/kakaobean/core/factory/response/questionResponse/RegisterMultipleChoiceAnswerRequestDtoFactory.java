package com.kakaobean.core.factory.response.questionResponse;

import com.kakaobean.core.response.application.dto.request.questionresponse.RegisterMultipleChoiceAnswerResponseRequestDto;

public class RegisterMultipleChoiceAnswerRequestDtoFactory {

    private RegisterMultipleChoiceAnswerRequestDtoFactory() {}

    /**
     * Multiple Choice Answer Service Dto 생성
     */
    public static RegisterMultipleChoiceAnswerResponseRequestDto createDto(Long answerId, String content){
        return RegisterMultipleChoiceAnswerResponseRequestDto
                .builder()
                .answerId(answerId)
                .content(content)
                .build();
    }
}
