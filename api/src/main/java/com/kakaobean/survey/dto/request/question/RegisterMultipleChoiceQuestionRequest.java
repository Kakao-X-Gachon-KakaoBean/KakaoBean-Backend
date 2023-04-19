package com.kakaobean.survey.dto.request.question;


import com.kakaobean.core.survey.application.dto.QuestionRequestType;
import com.kakaobean.core.survey.application.dto.question.RegisterMultipleChoiceQuestionRequestDto;
import com.kakaobean.core.survey.application.dto.question.RegisterQuestionRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class RegisterMultipleChoiceQuestionRequest extends RegisterQuestionRequest{

    private Integer numberOfAnswerChoices;
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
                finalQuestion
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
            QuestionRequestType type,
            Integer numberOfAnswerChoices,
            List<String> answers,
            List<RegisterQuestionFlowLogicRequest> logics,
            boolean finalQuestion
    ) {
        super(title, explanation, questionNumber, type, finalQuestion);
        this.numberOfAnswerChoices = numberOfAnswerChoices;
        this.answers = answers;
        this.logics = logics;
    }
}
