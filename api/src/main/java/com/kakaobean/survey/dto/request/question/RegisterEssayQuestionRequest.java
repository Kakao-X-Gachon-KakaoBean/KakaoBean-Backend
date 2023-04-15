package com.kakaobean.survey.dto.request.question;

import com.kakaobean.core.survey.application.dto.QuestionRequestType;
import com.kakaobean.core.survey.application.dto.question.RegisterEssayQuestionRequestDto;
import com.kakaobean.core.survey.application.dto.question.RegisterQuestionRequestDto;
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
                questionNumber
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
            QuestionRequestType type
    ) {
        super(title, explanation, questionNumber, type);
    }
}
