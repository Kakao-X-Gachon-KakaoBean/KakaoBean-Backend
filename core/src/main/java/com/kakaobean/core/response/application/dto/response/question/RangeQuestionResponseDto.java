package com.kakaobean.core.response.application.dto.response.question;

import com.kakaobean.core.survey.application.dto.QuestionDTOType;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class RangeQuestionResponseDto extends QuestionResponseDto{

    private int answer;

    public RangeQuestionResponseDto(Long questionId, QuestionDTOType questionType, int answer) {
        super(questionId, questionType);
        this.answer = answer;
    }
}
