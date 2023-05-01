package com.kakaobean.core.survey.application.dto.response.question;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FindEssayQuestionResponseDto extends FindQuestionResponseDto {

    @Builder
    public FindEssayQuestionResponseDto(Long questionId,
                                        String title,
                                        String explanation,
                                        String questionNumber,
                                        Boolean finalQuestion,
                                        String nextQuestion) {
        super(questionId, title, explanation, questionNumber, finalQuestion, nextQuestion);
    }

}
