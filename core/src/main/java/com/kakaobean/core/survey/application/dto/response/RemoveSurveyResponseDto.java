package com.kakaobean.core.survey.application.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RemoveSurveyResponseDto {

    private String message;

    public RemoveSurveyResponseDto(String message) {
        this.message = message;
    }
}
