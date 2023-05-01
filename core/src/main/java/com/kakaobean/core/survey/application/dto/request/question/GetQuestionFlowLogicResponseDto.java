package com.kakaobean.core.survey.application.dto.request.question;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class GetQuestionFlowLogicResponseDto {


    private List<GetMultipleChoiceQuestionAnswerDto> conditionOfQuestionAnswers;
    private String nextQuestionNumber;

    @Builder
    public GetQuestionFlowLogicResponseDto(List<GetMultipleChoiceQuestionAnswerDto> conditionOfQuestionAnswers, String nextQuestionNumber) {
        this.conditionOfQuestionAnswers = conditionOfQuestionAnswers;
        this.nextQuestionNumber = nextQuestionNumber;
    }
}
