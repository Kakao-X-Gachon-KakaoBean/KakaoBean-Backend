package com.kakaobean.core.response.application.dto.response;

import com.kakaobean.core.survey.application.dto.response.FindSurveyResponseDto;
import lombok.Getter;

import java.util.List;

@Getter
public class FindResponsesDto {

    private final FindSurveyResponseDto survey;
    private final List<SurveyResponseDto> responses;

    public FindResponsesDto(FindSurveyResponseDto surveyDto,
                            List<SurveyResponseDto> responsesDto) {
        this.survey = surveyDto;
        this.responses = responsesDto;
    }
}
