package com.kakaobean.unit.controller.factory.survey.response;

import com.kakaobean.core.survey.application.dto.response.FindSubmittedSurveyListResponseDto;
import com.kakaobean.core.survey.application.dto.response.FindSubmittedSurveyResponseDto;

import java.util.List;

public class FindSubmittedSurveyListResponseFactory {

    public static FindSubmittedSurveyListResponseDto create(){
        return new FindSubmittedSurveyListResponseDto(
                List.of(createDetailSubmittedSurveyDto(1L, 11L, "제출한 설문 제목1", "23. 5. 21. 오후 3:59"),
                        createDetailSubmittedSurveyDto(2L, 22L,"제출한 설문 제목2", "23. 5. 21. 오후 4:01")
                )
        );
    }

    public static FindSubmittedSurveyResponseDto createDetailSubmittedSurveyDto(Long surveyId,
                                                                                Long surveyResponseId,
                                                                                String surveyTitle,
                                                                                String submittedDate){
        return FindSubmittedSurveyResponseDto
                .builder()
                .surveyId(surveyId)
                .surveyResponseId(surveyResponseId)
                .surveyTitle(surveyTitle)
                .submittedDate(submittedDate)
                .build();
    }
}
