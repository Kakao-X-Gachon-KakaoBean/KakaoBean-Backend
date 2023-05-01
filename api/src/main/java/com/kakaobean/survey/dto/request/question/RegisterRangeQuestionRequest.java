package com.kakaobean.survey.dto.request.question;

import com.kakaobean.core.survey.application.dto.request.QuestionRequestType;
import com.kakaobean.core.survey.application.dto.response.question.RegisterQuestionRequestDto;
import com.kakaobean.core.survey.application.dto.response.question.RegisterRangeQuestionRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class RegisterRangeQuestionRequest extends RegisterQuestionRequest{

    private Integer min;
    private Integer max;

    @Override
    protected  RegisterQuestionRequestDto createDetailServiceDto() {
        return new RegisterRangeQuestionRequestDto(
                title,
                explanation,
                questionNumber,
                min,
                max,
                finalQuestion,
                nextQuestionNumber
        );
    }

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
            Integer max,
            Boolean finalQuestion,
            String nextQuestionNumber
    ) {
        super(title, explanation, questionNumber, type, finalQuestion, nextQuestionNumber);
        this.min = min;
        this.max = max;
    }
}
