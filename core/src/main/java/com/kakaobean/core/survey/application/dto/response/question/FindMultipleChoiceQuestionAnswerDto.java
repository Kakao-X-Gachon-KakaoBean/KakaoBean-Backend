package com.kakaobean.core.survey.application.dto.response.question;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FindMultipleChoiceQuestionAnswerDto {

    private Long answerId;
    private String content;

    @Builder
    public FindMultipleChoiceQuestionAnswerDto(Long answerId, String content) {
        this.answerId = answerId;
        this.content = content;
    }
}
