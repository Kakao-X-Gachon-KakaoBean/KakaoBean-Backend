package com.kakaobean.response.dto.request.questionresponse;

import com.kakaobean.core.response.application.dto.request.questionresponse.RegisterEssayQuestionResponseRequestDto;
import com.kakaobean.core.response.application.dto.request.questionresponse.RegisterQuestionResponseRequestDto;
import com.kakaobean.core.survey.application.dto.QuestionDTOType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterEssayQuestionResponseRequest extends RegisterQuestionResponseRequest{

    private String answers;

    public RegisterEssayQuestionResponseRequest(QuestionDTOType type, Long queestionId, String answers) {
        super(type, queestionId);
        this.answers = answers;
    }

    @Override
    protected RegisterQuestionResponseRequestDto createDetailServiceDto() {
        return new RegisterEssayQuestionResponseRequestDto(
            questionId, answers
        );
    }
}
