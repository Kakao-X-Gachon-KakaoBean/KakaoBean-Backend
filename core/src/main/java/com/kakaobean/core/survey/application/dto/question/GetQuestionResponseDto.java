package com.kakaobean.core.survey.application.dto.question;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakaobean.core.survey.application.dto.GetSurveyResponseDto;
import com.kakaobean.core.survey.domain.question.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = GetMultipleChoiceQuestionResonseDto.class, name = "MULTIPLE"),
        @JsonSubTypes.Type(value = GetEssayQuestionResponseDto.class, name = "ESSAY"),
        @JsonSubTypes.Type(value = GetRangeQuestionResponseDto.class, name = "RANGE"),
})
public abstract class GetQuestionResponseDto {

    protected String title;
    protected String explanation;
    protected String questionNumber;
    protected Boolean finalQuestion;
    private String nextQuestionNumber;

    public GetQuestionResponseDto(String title,
                                  String explanation,
                                  String questionNumber,
                                  Boolean finalQuestion,
                                  String nextQuestionNumber) {
        this.title = title;
        this.explanation = explanation;
        this.questionNumber = questionNumber;
        this.finalQuestion = finalQuestion;
        this.nextQuestionNumber = nextQuestionNumber;
    }

    public GetQuestionResponseDto(String nextQuestionNumber) {
        this.nextQuestionNumber = nextQuestionNumber;
    }



}
