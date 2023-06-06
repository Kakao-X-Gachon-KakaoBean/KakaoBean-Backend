package com.kakaobean.unit.controller.factory.response.response;

import com.kakaobean.core.response.application.dto.response.FindSurveyStatisticsResponseDto;
import com.kakaobean.unit.controller.factory.response.response.statistics.AgeRatioDtoFactory;
import com.kakaobean.unit.controller.factory.response.response.statistics.GenderRatioDtoFactory;
import com.kakaobean.unit.controller.factory.response.response.statistics.QuestionStatisticsDtoFactory;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class FindSurveyStatisticsResponseDtoFactory {

    public static FindSurveyStatisticsResponseDto create(){

        return new FindSurveyStatisticsResponseDto(
                2L,
                "surveyTitle",
                "2023-06-04",
                3,
                false,
                GenderRatioDtoFactory.createDtoList(),
                AgeRatioDtoFactory.createDtoList(),
                QuestionStatisticsDtoFactory.createDtoList()
        );
    }
}
