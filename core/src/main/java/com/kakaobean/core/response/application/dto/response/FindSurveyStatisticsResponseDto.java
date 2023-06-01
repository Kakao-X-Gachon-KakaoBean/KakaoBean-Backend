package com.kakaobean.core.response.application.dto.response;

import com.kakaobean.core.member.domain.Member;
import com.kakaobean.core.response.application.dto.response.statistics.AgeRatioDto;
import com.kakaobean.core.response.application.dto.response.statistics.GenderRatioDto;
import com.kakaobean.core.response.application.dto.response.statistics.QuestionResultDto;
import com.kakaobean.core.response.domain.SurveyResponse;
import com.kakaobean.core.survey.domain.Survey;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class FindSurveyStatisticsResponseDto {

    private Long surveyId;

    private String surveyTitle;

    private String surveyDate;

    private Integer numberOfResponse;

    private List<GenderRatioDto> surveyGenderPercent;

    private List<AgeRatioDto> surveyAgePercent;

    private List<QuestionResultDto> questionsResult;

    @Builder
    public FindSurveyStatisticsResponseDto(Survey mySurvey,
                                           Integer numberOfResponse,
                                           List<SurveyResponse> surveyResponses,
                                           List<Member> respondents) {
        this.surveyId = mySurvey.getId();
        this.surveyTitle = mySurvey.getTitle();
        this.surveyDate = Arrays.stream(mySurvey.getCreatedAt().split("."))  // 날짜 가공 ex) 2020-1-1
                .limit(3).collect(Collectors.joining("-")).replace(" ","");
        this.numberOfResponse = numberOfResponse;
        this.surveyGenderPercent = GenderRatioDto.calculateRatio(respondents, numberOfResponse);
        this.surveyAgePercent = AgeRatioDto.calculateRatio(respondents, numberOfResponse);
        this.questionsResult = QuestionResultDto.from(mySurvey.getQuestions());
    }

}
