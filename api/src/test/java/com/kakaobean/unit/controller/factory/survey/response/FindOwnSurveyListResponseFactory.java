package com.kakaobean.unit.controller.factory.survey.response;

import com.kakaobean.core.survey.application.dto.response.FindOwnSurveyListResponseDto;
import com.kakaobean.core.survey.application.dto.response.FindOwnSurveyResponseDto;

import java.util.List;

public class FindOwnSurveyListResponseFactory {

    public static FindOwnSurveyListResponseDto create(){
        return new FindOwnSurveyListResponseDto(
                List.of(createDetailOwnSurveyDto(1L, "내가 만든 설문 제목", 6),
                        createDetailOwnSurveyDto(2L, "내가 만든 설문 제목2", 6),
                        createDetailOwnSurveyDto(3L, "내가 만든 설문 제목3", 1))
        );
    }

    public static FindOwnSurveyResponseDto createDetailOwnSurveyDto(Long surveyId,
                                                                    String surveyTitle,
                                                                    Integer numberOfResponse){
        return FindOwnSurveyResponseDto
                .builder()
                .surveyId(surveyId)
                .surveyTitle(surveyTitle)
                .numberOfResponse(numberOfResponse)
                .build();
    }

}
