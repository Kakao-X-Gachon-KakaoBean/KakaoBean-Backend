package com.kakaobean.response.dto.request.questionresponse;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import com.kakaobean.core.response.application.dto.request.questionresponse.RegisterQuestionResponseRequestDto;
import com.kakaobean.core.survey.application.dto.QuestionDTOType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = RegisterMultipleChoiceQuestionResponseRequest.class, name = "MULTIPLE"),
        @JsonSubTypes.Type(value = RegisterEssayQuestionResponseRequest.class, name = "ESSAY"),
        @JsonSubTypes.Type(value = RegisterRangeQuestionResponseRequest.class, name = "RANGE"),
})
public abstract class RegisterQuestionResponseRequest {

    @NotNull
    protected QuestionDTOType type;

    @Positive
    protected Long questionId;

    public RegisterQuestionResponseRequestDto toServiceDto(){
        return createDetailServiceDto();
    }

    protected abstract RegisterQuestionResponseRequestDto createDetailServiceDto();

    public RegisterQuestionResponseRequest(QuestionDTOType type, Long questionId) {
        this.type = type;
        this.questionId = questionId;
    }
}
