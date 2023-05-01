package com.kakaobean.core.survey.application.dto.response.question;


import com.kakaobean.core.survey.domain.question.Question;
import com.kakaobean.core.survey.domain.question.multiplechoice.MultipleChoiceQuestion;
import com.kakaobean.core.survey.domain.question.multiplechoice.MultipleChoiceQuestionAnswer;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static java.util.stream.Collectors.*;

@Getter
@NoArgsConstructor
public class RegisterMultipleChoiceQuestionRequestDto extends RegisterQuestionRequestDto{

    private Integer numberOfAnswerChoices;
    private List<String> answers;
    private List<RegisterQuestionFlowLogicRequestDto> conditions;

    @Builder
    public RegisterMultipleChoiceQuestionRequestDto(String title,
                                                    String explanation,
                                                    String questionNumber,
                                                    Integer numberOfAnswerChoices,
                                                    List<String> answers,
                                                    List<RegisterQuestionFlowLogicRequestDto> conditions,
                                                    Boolean finalQuestion,
                                                    String nextQuestionNumber) {
        super(title, explanation, questionNumber, finalQuestion, nextQuestionNumber);
        this.numberOfAnswerChoices = numberOfAnswerChoices;
        this.answers = answers;
        this.conditions = conditions;
    }

    @Override
    protected Question createdDetailQuestionEntity() {
        MultipleChoiceQuestion question = new MultipleChoiceQuestion(
                title,
                explanation,
                questionNumber,
                createMultipleChoiceAnswers(),
                numberOfAnswerChoices,
                finalQuestion
        );

        return question;
    }

    private List<MultipleChoiceQuestionAnswer> createMultipleChoiceAnswers() {
        return answers.stream()
                .map(MultipleChoiceQuestionAnswer::new)
                .collect(toList());
    }

    @Override
    public boolean hasQuestionFlowLogic() {
        return true;
    }
}
