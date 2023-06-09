package com.kakaobean.survey.dto.request.question;


import com.kakaobean.core.survey.application.dto.QuestionDTOType;
import com.kakaobean.core.survey.application.dto.request.question.RegisterMultipleChoiceQuestionRequestDto;
import com.kakaobean.core.survey.application.dto.request.question.RegisterQuestionRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class RegisterMultipleChoiceQuestionRequest extends RegisterQuestionRequest{

    @NotNull
    private Integer numberOfAnswerChoices;

    @NotEmpty
    private List<String> answers;

    private List<RegisterQuestionFlowLogicRequest> logics;

    @Override
    protected RegisterQuestionRequestDto createDetailServiceDto() {
        return new RegisterMultipleChoiceQuestionRequestDto(
                title,
                explanation,
                questionNumber,
                numberOfAnswerChoices,
                answers,
                logics.stream()
                        .map(logic -> logic.toServiceDto())
                        .collect(Collectors.toList()),
                finalQuestion,
                nextQuestionNumber
        );
    }

    /**
     * 테스트 코드에서만 사용할 것.
     */
    @Builder
    public RegisterMultipleChoiceQuestionRequest(
            String title,
            String explanation,
            String questionNumber,
            QuestionDTOType type,
            Integer numberOfAnswerChoices,
            List<String> answers,
            List<RegisterQuestionFlowLogicRequest> logics,
            boolean finalQuestion,
            String nextQuestionNumber
    ) {
        super(title, explanation, questionNumber, type, finalQuestion, nextQuestionNumber);
        this.numberOfAnswerChoices = numberOfAnswerChoices;
        this.answers = answers;
        this.logics = logics;
    }
}
