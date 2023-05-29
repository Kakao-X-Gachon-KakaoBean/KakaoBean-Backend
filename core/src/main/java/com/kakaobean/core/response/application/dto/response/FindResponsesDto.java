package com.kakaobean.core.response.application.dto.response;

import com.kakaobean.core.survey.application.dto.response.FindSurveyResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class FindResponsesDto {

    private FindSurveyResponseDto survey;
    private List<SurveyResponseDto> responses;

    public FindResponsesDto(FindSurveyResponseDto surveyDto,
                            List<SurveyResponseDto> responsesDto) {
        this.survey = surveyDto;
        this.responses = responsesDto;
    }
}
