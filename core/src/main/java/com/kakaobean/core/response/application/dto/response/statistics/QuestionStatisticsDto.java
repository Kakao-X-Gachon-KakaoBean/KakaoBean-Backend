package com.kakaobean.core.response.application.dto.response.statistics;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.kakaobean.core.response.application.dto.response.SurveyResponseDto;
import com.kakaobean.core.survey.application.dto.QuestionDTOType;
import com.kakaobean.core.survey.domain.question.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = MultipleQuestionStatisticsDto.class, name = "MULTIPLE"),
        @JsonSubTypes.Type(value = EssayQuestionStatisticsDto.class, name = "ESSAY"),
        @JsonSubTypes.Type(value = RangeQuestionStatisticsDto.class, name = "RANGE"),
})
public abstract class QuestionStatisticsDto {

    private QuestionDTOType type;
    private String title;
    private String explanation;

    public QuestionStatisticsDto(QuestionDTOType type, String title, String explanation) {
        this.type = type;
        this.title = title;
        this.explanation = explanation;
    }

    public static QuestionStatisticsDto from(Question question,
                                             List<SurveyResponseDto> allResponses){

        return QuestionStatisticsDtoFactory.createDto(question, allResponses);
    }

}
