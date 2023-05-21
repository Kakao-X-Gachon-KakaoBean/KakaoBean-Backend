package com.kakaobean.core.survey.application.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FindSubmittedSurveyResponseDto {

    private Long surveyId;

    private Long surveyResponseId;

    private String surveyTitle;

    private String submittedDate;

    @Builder
    public FindSubmittedSurveyResponseDto(Long surveyId,
                                          Long surveyResponseId,
                                          String surveyTitle,
                                          String submittedDate) {
        this.surveyId = surveyId;
        this.surveyResponseId = surveyResponseId;
        this.surveyTitle = surveyTitle;
        this.submittedDate = submittedDate;
    }
}
