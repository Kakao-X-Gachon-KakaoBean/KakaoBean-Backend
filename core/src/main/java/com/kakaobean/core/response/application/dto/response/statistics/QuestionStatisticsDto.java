package com.kakaobean.core.response.application.dto.response.statistics;

import com.kakaobean.core.response.application.dto.response.SurveyResponseDto;
import com.kakaobean.core.survey.application.dto.QuestionDTOType;
import com.kakaobean.core.survey.domain.question.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
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
                                             Integer numberOfResponse,
                                             List<SurveyResponseDto> allResponses){

        return QuestionStatisticsDtoFactory.createDto(question, numberOfResponse, allResponses);
    }

}
