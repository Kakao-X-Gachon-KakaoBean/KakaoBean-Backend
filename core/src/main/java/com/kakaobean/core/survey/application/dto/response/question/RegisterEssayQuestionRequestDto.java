package com.kakaobean.core.survey.application.dto.response.question;

import com.kakaobean.core.survey.domain.question.Question;
import com.kakaobean.core.survey.domain.question.essay.EssayQuestion;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterEssayQuestionRequestDto extends RegisterQuestionRequestDto{

    @Builder
    public RegisterEssayQuestionRequestDto(String title,
                                           String explanation,
                                           String questionNumber,
                                           Boolean finalQuestion,
                                           String nextQuestionNumber) {
        super(title, explanation, questionNumber, finalQuestion, nextQuestionNumber);
    }

    @Override
    protected Question createdDetailQuestionEntity() {
        return new EssayQuestion(title, explanation, questionNumber, finalQuestion);
    }

    @Override
    public boolean hasQuestionFlowLogic() {
        return false;
    }
}
