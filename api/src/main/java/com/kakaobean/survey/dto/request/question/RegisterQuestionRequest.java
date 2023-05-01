package com.kakaobean.survey.dto.request.question;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.kakaobean.core.survey.application.dto.request.QuestionRequestType;
import com.kakaobean.core.survey.application.dto.request.question.RegisterQuestionRequestDto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.*;

@Getter
@NoArgsConstructor
@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = RegisterMultipleChoiceQuestionRequest.class, name = "MULTIPLE"),
        @JsonSubTypes.Type(value = RegisterEssayQuestionRequest.class, name = "ESSAY"),
        @JsonSubTypes.Type(value = RegisterRangeQuestionRequest.class, name = "RANGE"),
})
public abstract class RegisterQuestionRequest {

    @NotBlank
    protected String title;

    @NotBlank
    protected String explanation;

    @NotBlank
    protected String questionNumber;

    @NotNull
    protected QuestionRequestType type;

    @NotNull
    protected Boolean finalQuestion;

    @NotBlank
    protected String nextQuestionNumber;

    public RegisterQuestionRequestDto toServiceDto(){
        return createDetailServiceDto();
    }

    protected abstract RegisterQuestionRequestDto createDetailServiceDto();

    /**
     * 테스트 코드에서만 사용할 것.
     */
    public RegisterQuestionRequest(
            String title,
            String explanation,
            String questionNumber,
            QuestionRequestType type,
            Boolean finalQuestion,
            String nextQuestionNumber
    ) {
        this.title = title;
        this.explanation = explanation;
        this.questionNumber = questionNumber;
        this.type = type;
        this.finalQuestion = finalQuestion;
        this.nextQuestionNumber = nextQuestionNumber;
    }
}

