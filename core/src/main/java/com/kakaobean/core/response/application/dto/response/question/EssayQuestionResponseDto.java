package com.kakaobean.core.response.application.dto.response.question;

import lombok.Getter;

@Getter
public class EssayQuestionResponseDto extends QuestionResponseDto {

    private final String answer;

    public EssayQuestionResponseDto(Long id, String answer) {
        super(id);
        this.answer = answer;
    }
}
