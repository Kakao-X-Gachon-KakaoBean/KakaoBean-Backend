package com.kakaobean.core.survey.application.dto.question;

import com.kakaobean.core.survey.domain.question.Question;
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
                                       Question nextQuestion) {
        super(title, explanation, questionNumber, finalQuestion, nextQuestion.getQuestionNumber());
    }


}
