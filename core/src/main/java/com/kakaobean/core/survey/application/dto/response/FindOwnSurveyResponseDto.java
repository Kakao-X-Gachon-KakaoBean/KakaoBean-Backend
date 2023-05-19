package com.kakaobean.core.survey.application.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FindOwnSurveyResponseDto {

    private Long SurveyId;

    private String SurveyTitle;

    private Integer numberOfResponse;

    @Builder
    public FindOwnSurveyResponseDto(Long surveyId, String surveyTitle, Integer numberOfResponse) {
        this.SurveyId = surveyId;
        this.SurveyTitle = surveyTitle;
        this.numberOfResponse = numberOfResponse;
    }
}
