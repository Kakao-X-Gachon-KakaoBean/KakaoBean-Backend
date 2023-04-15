package com.kakaobean.survey.dto.request.question;

import com.kakaobean.core.survey.application.dto.QuestionRequestType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterRangeQuestionRequest extends RegisterQuestionRequest{
    private Integer min;
    private Integer max;

    /**
     * 테스트 코드에서만 사용할 것.
     */
    @Builder
    public RegisterRangeQuestionRequest(
            String title,
            String explanation,
            String questionNumber,
            QuestionRequestType type,
            Integer min,
            Integer max
    ) {
        super(title, explanation, questionNumber, type);
        this.min = min;
        this.max = max;
    }
}
