package com.kakaobean.core.survey.application.dto.response.question;

import com.kakaobean.core.survey.application.dto.QuestionDTOType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.kakaobean.core.survey.application.dto.QuestionDTOType.*;


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
        super(questionId, title, explanation, questionNumber, finalQuestion, nextQuestionNumber, RANGE);
        this.min = min;
        this.max = max;
    }

}
