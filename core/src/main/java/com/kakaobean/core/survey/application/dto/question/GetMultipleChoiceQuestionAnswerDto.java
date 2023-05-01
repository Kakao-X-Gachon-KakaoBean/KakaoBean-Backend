package com.kakaobean.core.survey.application.dto.question;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetMultipleChoiceQuestionAnswerDto {

    private Long answerId;
    private String content;

    @Builder
    public GetMultipleChoiceQuestionAnswerDto(Long answerId, String content) {
        this.answerId = answerId;
        this.content = content;
    }
}
