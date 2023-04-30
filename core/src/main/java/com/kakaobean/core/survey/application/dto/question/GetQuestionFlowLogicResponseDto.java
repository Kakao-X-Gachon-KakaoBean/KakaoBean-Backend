package com.kakaobean.core.survey.application.dto.question;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class GetQuestionFlowLogicResponseDto {

    private List<String> conditionOfQuestionAnswers;
    private String nextQuestionNumber;

    @Builder
    public GetQuestionFlowLogicResponseDto(List<String> conditionOfQuestionAnswers, String nextQuestionNumber) {
        this.conditionOfQuestionAnswers = conditionOfQuestionAnswers;
        this.nextQuestionNumber = nextQuestionNumber;
    }
}
