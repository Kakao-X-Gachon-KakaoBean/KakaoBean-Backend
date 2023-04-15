package com.kakaobean.core.survey.application.dto.question;

import com.kakaobean.core.survey.domain.question.Question;
import com.kakaobean.core.survey.domain.question.essay.EssayQuestion;

import com.kakaobean.core.survey.domain.question.multiplechoice.MultipleChoiceQuestionAnswer;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RegisterEssayQuestionRequestDto extends RegisterQuestionRequestDto{

    public RegisterEssayQuestionRequestDto(
            String title,
            String explanation,
            String questionNumber
    ) {
        super(title, explanation, questionNumber);
    }

    @Override
    protected Question createdDetailQuestionEntity() {
        return new EssayQuestion(title, explanation, questionNumber);
    }

    @Override
    public boolean hasQuestionFlowLogic() {
        return false;
    }
}
