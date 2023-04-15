package com.kakaobean.survey.dto.request.question;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.kakaobean.core.survey.application.dto.QuestionRequestType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    private String title;
    private String explanation;
    private String questionNumber;
    private QuestionRequestType type;

    /**
     * 테스트 코드에서만 사용할 것.
     */
    public RegisterQuestionRequest(String title, String explanation, String questionNumber, QuestionRequestType type) {
        this.title = title;
        this.explanation = explanation;
        this.questionNumber = questionNumber;
        this.type = type;
    }
}
