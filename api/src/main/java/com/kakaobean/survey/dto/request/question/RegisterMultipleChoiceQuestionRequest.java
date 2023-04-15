package com.kakaobean.survey.dto.request.question;


import com.kakaobean.core.survey.application.dto.QuestionRequestType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RegisterMultipleChoiceQuestionRequest extends RegisterQuestionRequest{

    private Integer numberOfAnswerChoices;
    private List<String> answers;
    private List<RegisterQuestionFlowLogicRequest> logics;


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
            List<RegisterQuestionFlowLogicRequest> logics
    ) {
        super(title, explanation, questionNumber, type);
        this.numberOfAnswerChoices = numberOfAnswerChoices;
        this.answers = answers;
        this.logics = logics;
    }
}
