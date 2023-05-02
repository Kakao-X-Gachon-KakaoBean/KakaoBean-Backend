package com.kakaobean.core.survey.application.dto.response.question;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class FindRangeQuestionResponseDto extends FindQuestionResponseDto {

    private Integer min;
    private Integer max;

    @Builder
    public FindRangeQuestionResponseDto(Long questionId,
                                        String title,
                                        String explanation,
                                        String questionNumber,
                                        Boolean finalQuestion,
                                        String nextQuestionNumber,
                                        Integer min,
                                        Integer max) {
        super(questionId, title, explanation, questionNumber, finalQuestion, nextQuestionNumber);
        this.min = min;
        this.max = max;
    }

}
