package com.kakaobean.core.survey.application.dto.response.question;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = FindMultipleChoiceQuestionResonseDto.class, name = "MULTIPLE"),
        @JsonSubTypes.Type(value = FindEssayQuestionResponseDto.class, name = "ESSAY"),
        @JsonSubTypes.Type(value = FindRangeQuestionResponseDto.class, name = "RANGE"),
})
public abstract class FindQuestionResponseDto {

    private Long questionId;
    protected String title;
    protected String explanation;
    protected String questionNumber;
    protected Boolean finalQuestion;
    private String nextQuestionNumber;

    public FindQuestionResponseDto(Long questionId,
                                   String title,
                                   String explanation,
                                   String questionNumber,
                                   Boolean finalQuestion,
                                   String nextQuestionNumber) {
        this.questionId = questionId;
        this.title = title;
        this.explanation = explanation;
        this.questionNumber = questionNumber;
        this.finalQuestion = finalQuestion;
        this.nextQuestionNumber = nextQuestionNumber;
    }

}
