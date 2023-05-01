package com.kakaobean.core.survey.application.dto.question;

import com.kakaobean.core.survey.domain.question.Question;
import com.kakaobean.core.survey.domain.question.multiplechoice.MultipleChoiceQuestion;
import com.kakaobean.core.survey.domain.question.multiplechoice.MultipleChoiceQuestionAnswer;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class GetMultipleChoiceQuestionResonseDto extends GetQuestionResponseDto{

    private Integer numberOfAnswerChoices;
    private List<GetMultipleChoiceQuestionAnswerDto> answers;
    private List<GetQuestionFlowLogicResponseDto> logics;

    @Builder
    public GetMultipleChoiceQuestionResonseDto(Long questionId,
                                               String title,
                                               String explanation,
                                               String questionNumber,
                                               Boolean finalQuestion,
                                               String nextQuestion,
                                               Integer numberOfAnswerChoices,
                                               List<GetMultipleChoiceQuestionAnswerDto> answers,
                                               List<GetQuestionFlowLogicResponseDto> logics) {
        super(questionId, title, explanation, questionNumber, finalQuestion, nextQuestion);
        this.numberOfAnswerChoices = numberOfAnswerChoices;
        this.answers = answers;
        this.logics = logics;
    }

}
