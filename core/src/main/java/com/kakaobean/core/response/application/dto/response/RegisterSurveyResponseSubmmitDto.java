package com.kakaobean.core.response.application.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterSurveyResponseSubmmitDto {

    private Long surveyResponseId;

    public RegisterSurveyResponseSubmmitDto(Long surveyResponseId) {
        this.surveyResponseId = surveyResponseId;
    }
}
