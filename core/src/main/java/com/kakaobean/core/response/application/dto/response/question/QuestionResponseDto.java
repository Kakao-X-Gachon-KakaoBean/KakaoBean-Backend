package com.kakaobean.core.response.application.dto.response.question;

import com.kakaobean.core.response.domain.questionresponse.QuestionResponse;
import lombok.Getter;

@Getter
public abstract class QuestionResponseDto {

    private final Long questionId;

    public QuestionResponseDto(Long questionId) {
        this.questionId = questionId;
    }

    public static QuestionResponseDto from(QuestionResponse questionResponse) {
        return QuestionResponseDtoFactory.createDto(questionResponse);
    }
}
