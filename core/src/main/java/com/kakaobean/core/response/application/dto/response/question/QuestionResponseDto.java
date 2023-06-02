package com.kakaobean.core.response.application.dto.response.question;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.kakaobean.core.response.domain.questionresponse.QuestionResponse;
import com.kakaobean.core.survey.application.dto.QuestionDTOType;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = MultipleChoiceQuestionResponseDto.class, name = "MULTIPLE"),
        @JsonSubTypes.Type(value = EssayQuestionResponseDto.class, name = "ESSAY"),
        @JsonSubTypes.Type(value = RangeQuestionResponseDto.class, name = "RANGE"),
})
public abstract class QuestionResponseDto {

    private Long questionId;
    private QuestionDTOType type;
    private String title;

    public QuestionResponseDto(Long questionId, QuestionDTOType questionType) {
        this.questionId = questionId;
        this.type = questionType;
    }

    public QuestionResponseDto(Long questionId, QuestionDTOType type, String title) {
        this.questionId = questionId;
        this.type = type;
        this.title = title;
    }

    public static QuestionResponseDto from(QuestionResponse questionResponse) {
        return QuestionResponseDtoFactory.createDto(questionResponse);
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
