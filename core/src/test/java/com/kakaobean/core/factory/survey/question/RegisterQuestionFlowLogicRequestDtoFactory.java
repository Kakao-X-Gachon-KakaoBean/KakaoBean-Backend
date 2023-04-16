package com.kakaobean.core.factory.survey.question;

import com.kakaobean.core.survey.application.dto.question.RegisterQuestionFlowLogicRequestDto;

import java.util.List;

public class RegisterQuestionFlowLogicRequestDtoFactory {

    private RegisterQuestionFlowLogicRequestDtoFactory(){}

    public static RegisterQuestionFlowLogicRequestDto createDto(
            String nextQuestionNumber, String... answers
    ) {
        return RegisterQuestionFlowLogicRequestDto.builder()
                .conditionOfQuestionAnswers(
                        List.of(answers[0], answers[1])
                )
                .nextQuestionNumber(nextQuestionNumber)
                .build();
    }
}
