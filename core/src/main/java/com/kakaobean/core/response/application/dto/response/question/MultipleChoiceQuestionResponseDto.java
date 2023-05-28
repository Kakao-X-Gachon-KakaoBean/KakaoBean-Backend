package com.kakaobean.core.response.application.dto.response.question;

import lombok.Getter;

import java.util.List;

@Getter
public class MultipleChoiceQuestionResponseDto extends QuestionResponseDto{

    private final List<String> content;

    public MultipleChoiceQuestionResponseDto(Long questionId, List<String> content) {
        super(questionId);
        this.content = content;
    }
}
