package com.kakaobean.core.survey.application.dto.question;

import com.kakaobean.core.survey.domain.question.Question;
import com.kakaobean.core.survey.domain.question.multiplechoice.MultipleChoiceQuestionAnswer;
import com.kakaobean.core.survey.domain.question.range.RangeQuestion;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RegisterRangeQuestionRequestDto extends RegisterQuestionRequestDto{

    private Integer min;
    private Integer max;

    public RegisterRangeQuestionRequestDto(
            String title,
            String explanation,
            String questionNumber,
            Integer min,
            Integer max
    ) {
        super(title, explanation, questionNumber);
        this.min = min;
        this.max = max;
    }

    @Override
    protected Question createdDetailQuestionEntity() {
        return new RangeQuestion(
                title,
                explanation,
                questionNumber,
                min,
                max
        );
    }

    @Override
    public boolean hasQuestionFlowLogic() {
        return false;
    }
}
