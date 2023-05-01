package com.kakaobean.core.survey.application.dto.question;

import com.kakaobean.core.survey.domain.question.Question;
import com.kakaobean.core.survey.domain.question.essay.EssayQuestion;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetEssayQuestionResponseDto extends GetQuestionResponseDto{

    @Builder
    public GetEssayQuestionResponseDto(Long questionId,
                                       String title,
                                       String explanation,
                                       String questionNumber,
                                       Boolean finalQuestion,
                                       String nextQuestion) {
        super(questionId, title, explanation, questionNumber, finalQuestion, nextQuestion);
    }

}
