package com.kakaobean.core.survey.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
public class RegisterSurveyResponseDto {
    private Long surveyId;

    public RegisterSurveyResponseDto(Long id) {
        this.surveyId = id;
    }
}
