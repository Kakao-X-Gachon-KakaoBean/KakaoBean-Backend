package com.kakaobean.core.response.application.dto.request.questionresponse;

import com.kakaobean.core.response.domain.questionresponse.QuestionResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public abstract class RegisterQuestionResponseRequestDto {

    protected Long questionId;

    public RegisterQuestionResponseRequestDto(Long questionId) {
        this.questionId = questionId;
    }

    public QuestionResponse toEntity() {
        return createdDetailQuestionResponseEntity();
    }

    protected abstract QuestionResponse createdDetailQuestionResponseEntity();

}
