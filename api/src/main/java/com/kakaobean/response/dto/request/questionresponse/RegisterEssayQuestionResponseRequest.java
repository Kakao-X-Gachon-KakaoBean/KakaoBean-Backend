package com.kakaobean.response.dto.request.questionresponse;

import com.kakaobean.core.response.application.dto.request.questionresponse.RegisterEssayQuestionResponseRequestDto;
import com.kakaobean.core.response.application.dto.request.questionresponse.RegisterQuestionResponseRequestDto;
import com.kakaobean.core.survey.application.dto.QuestionDTOType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class RegisterEssayQuestionResponseRequest extends RegisterQuestionResponseRequest{

    private String answer;

    public RegisterEssayQuestionResponseRequest(QuestionDTOType type, Long queestionId, String answer) {
        super(type, queestionId);
        this.answer = answer;
    }

    @Override
    protected RegisterQuestionResponseRequestDto createDetailServiceDto() {
        return new RegisterEssayQuestionResponseRequestDto(
            questionId, answer
        );
    }
}
