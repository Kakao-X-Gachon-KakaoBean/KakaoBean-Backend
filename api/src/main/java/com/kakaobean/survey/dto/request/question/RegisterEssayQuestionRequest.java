package com.kakaobean.survey.dto.request.question;

import com.kakaobean.core.survey.application.dto.QuestionDTOType;
import com.kakaobean.core.survey.application.dto.request.question.RegisterEssayQuestionRequestDto;
import com.kakaobean.core.survey.application.dto.request.question.RegisterQuestionRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterEssayQuestionRequest extends RegisterQuestionRequest{

    @Override
    protected RegisterQuestionRequestDto createDetailServiceDto() {
        return new RegisterEssayQuestionRequestDto(
                title,
                explanation,
                questionNumber,
                finalQuestion,
                nextQuestionNumber
        );
    }

    /**
     * 테스트 코드에서만 사용할 것.
     */
    @Builder
    public RegisterEssayQuestionRequest(
            String title,
            String explanation,
            String questionNumber,
            QuestionDTOType type,
            boolean finalQuestion,
            String nextQuestionNumber
    ) {
        super(title, explanation, questionNumber, type, finalQuestion, nextQuestionNumber);
    }
}
