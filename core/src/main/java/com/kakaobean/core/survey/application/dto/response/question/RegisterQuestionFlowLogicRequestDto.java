package com.kakaobean.core.survey.application.dto.response.question;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RegisterQuestionFlowLogicRequestDto {

    private List<String> conditionOfQuestionAnswers;
    private String nextQuestionNumber;

    @Builder
    public RegisterQuestionFlowLogicRequestDto(List<String> conditionOfQuestionAnswers, String nextQuestionNumber) {
        this.conditionOfQuestionAnswers = conditionOfQuestionAnswers;
        this.nextQuestionNumber = nextQuestionNumber;
    }
}
