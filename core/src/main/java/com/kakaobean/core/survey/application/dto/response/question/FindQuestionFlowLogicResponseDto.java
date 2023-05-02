package com.kakaobean.core.survey.application.dto.response.question;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class FindQuestionFlowLogicResponseDto {


    private List<FindMultipleChoiceQuestionAnswerDto> conditionOfQuestionAnswers;
    private String nextQuestionNumber;

    @Builder
    public FindQuestionFlowLogicResponseDto(List<FindMultipleChoiceQuestionAnswerDto> conditionOfQuestionAnswers, String nextQuestionNumber) {
        this.conditionOfQuestionAnswers = conditionOfQuestionAnswers;
        this.nextQuestionNumber = nextQuestionNumber;
    }
}
