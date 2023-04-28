package com.kakaobean.core.survey.application.dto.question;

import com.kakaobean.core.survey.domain.question.Question;
import com.kakaobean.core.survey.domain.question.range.RangeQuestion;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterRangeQuestionRequestDto extends RegisterQuestionRequestDto{

    private Integer min;
    private Integer max;
    private String nextQuestionNumber;

    @Builder
    public RegisterRangeQuestionRequestDto(String title,
                                           String explanation,
                                           String questionNumber,
                                           Integer min,
                                           Integer max,
                                           Boolean finalQuestion,
                                           String nextQuestionNumber) {
        super(title, explanation, questionNumber, finalQuestion, nextQuestionNumber);
        this.min = min;
        this.max = max;
        this.nextQuestionNumber = nextQuestionNumber;
    }

    @Override
    protected Question createdDetailQuestionEntity() {
        return new RangeQuestion(
                title,
                explanation,
                questionNumber,
                min,
                max,
                finalQuestion
        );
    }

    @Override
    public boolean hasQuestionFlowLogic() {
        return false;
    }


}
