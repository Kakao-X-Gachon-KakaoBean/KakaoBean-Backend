package com.kakaobean.core.survey.application.dto.question;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetEssayQuestionResponseDto extends GetQuestionResponseDto{

    @Builder
    public GetEssayQuestionResponseDto(String title,
                                       String explanation,
                                       String questionNumber,
                                       Boolean finalQuestion,
                                       String nextQuestion) {
        super(title, explanation, questionNumber, finalQuestion, nextQuestion);
    }


}
