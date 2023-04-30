package com.kakaobean.core.survey.application.dto.question;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class GetRangeQuestionResponseDto extends GetQuestionResponseDto{

    private Integer min;
    private Integer max;

    @Builder
    public GetRangeQuestionResponseDto(String title,
                                       String explanation,
                                       String questionNumber,
                                       Boolean finalQuestion,
                                       String nextQuestion,
                                       Integer min,
                                       Integer max) {
        super(title, explanation, questionNumber, finalQuestion, nextQuestion);
        this.min = min;
        this.max = max;
    }

}
