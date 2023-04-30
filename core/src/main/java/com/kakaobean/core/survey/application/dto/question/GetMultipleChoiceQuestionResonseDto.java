package com.kakaobean.core.survey.application.dto.question;

import com.kakaobean.core.survey.domain.question.Question;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class GetMultipleChoiceQuestionResonseDto extends GetQuestionResponseDto{

    private Integer numberOfAnswerChoices;
    private List<String> answers;
    private List<GetQuestionFlowLogicResponseDto> logics;

    @Builder
    public GetMultipleChoiceQuestionResonseDto(String title,
                                               String explanation,
                                               String questionNumber,
                                               Boolean finalQuestion,
                                               Question nextQuestion,
                                               Integer numberOfAnswerChoices,
                                               List<String> answers,
                                               List<GetQuestionFlowLogicResponseDto> logics) {
        super(title, explanation, questionNumber, finalQuestion, nextQuestion.getQuestionNumber());
        this.numberOfAnswerChoices = numberOfAnswerChoices;
        this.answers = answers;
        this.logics = logics;
    }

}
