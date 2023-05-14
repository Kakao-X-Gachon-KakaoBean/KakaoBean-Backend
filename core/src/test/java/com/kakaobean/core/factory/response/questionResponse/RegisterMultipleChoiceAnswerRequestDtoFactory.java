package com.kakaobean.core.factory.response.questionResponse;

import com.kakaobean.core.response.application.dto.request.questionresponse.RegisterMultipleChoiceAnswerResponseRequestDto;

public class RegisterMultipleChoiceAnswerRequestDtoFactory {

    private RegisterMultipleChoiceAnswerRequestDtoFactory() {}

    public static RegisterMultipleChoiceAnswerResponseRequestDto createDto(Long answerId, String content){
        return RegisterMultipleChoiceAnswerResponseRequestDto
                .builder()
                .answerId(answerId)
                .content(content)
                .build();
    }
}
