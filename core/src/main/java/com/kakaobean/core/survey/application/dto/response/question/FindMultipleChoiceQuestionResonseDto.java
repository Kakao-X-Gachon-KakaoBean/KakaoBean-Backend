package com.kakaobean.core.survey.application.dto.response.question;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class FindMultipleChoiceQuestionResonseDto extends FindQuestionResponseDto {

    private Integer numberOfAnswerChoices;
    private List<FindMultipleChoiceQuestionAnswerDto> answers;
    private List<FindQuestionFlowLogicResponseDto> logics;

    @Builder
    public FindMultipleChoiceQuestionResonseDto(Long questionId,
                                                String title,
                                                String explanation,
                                                String questionNumber,
                                                Boolean finalQuestion,
                                                String nextQuestion,
                                                Integer numberOfAnswerChoices,
                                                List<FindMultipleChoiceQuestionAnswerDto> answers,
                                                List<FindQuestionFlowLogicResponseDto> logics) {
        super(questionId, title, explanation, questionNumber, finalQuestion, nextQuestion);
        this.numberOfAnswerChoices = numberOfAnswerChoices;
        this.answers = answers;
        this.logics = logics;
    }

}
