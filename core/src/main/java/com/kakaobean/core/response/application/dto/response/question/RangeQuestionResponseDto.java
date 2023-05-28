package com.kakaobean.core.response.application.dto.response.question;

import lombok.Getter;


@Getter
public class RangeQuestionResponseDto extends QuestionResponseDto{

    private final int answer;

    public RangeQuestionResponseDto(Long id, int answer) {
        super(id);
        this.answer = answer;
    }
}
