package com.kakaobean.core.response.application.dto.request.questionresponse;

import com.kakaobean.core.response.domain.questionresponse.EssayQuestionResponse;
import com.kakaobean.core.response.domain.questionresponse.QuestionResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterEssayQuestionResponseRequestDto extends RegisterQuestionResponseRequestDto{

    private String answer;

    public RegisterEssayQuestionResponseRequestDto(Long questionId, String answer) {
        super(questionId);
        this.answer = answer;
    }

    @Override
    protected QuestionResponse createdDetailQuestionResponseEntity() {
        return new EssayQuestionResponse(questionId, answer);
    }
}
