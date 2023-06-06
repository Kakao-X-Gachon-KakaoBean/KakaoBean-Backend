package com.kakaobean.core.response.application.dto.response;

import com.kakaobean.core.member.domain.Member;
import com.kakaobean.core.response.application.dto.response.statistics.AgeRatioDto;
import com.kakaobean.core.response.application.dto.response.statistics.GenderRatioDto;
import com.kakaobean.core.response.application.dto.response.statistics.QuestionStatisticsDto;
import com.kakaobean.core.survey.domain.CloseStatus;
import com.kakaobean.core.survey.domain.Survey;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    private Boolean closeStatus;

    private List<GenderRatioDto> surveyGenderPercent;

    private List<AgeRatioDto> surveyAgePercent;

    private List<QuestionStatisticsDto> questionsResult;

    public FindSurveyStatisticsResponseDto(Survey mySurvey,
                                           Integer numberOfResponse,
                                           List<Member> respondents,
                                           List<SurveyResponseDto> allResponses
    ) {
        this.surveyId = mySurvey.getId();
        this.surveyTitle = mySurvey.getTitle();
        this.surveyDate = changeDateFormat(mySurvey); //2022-01-01
        this.numberOfResponse = numberOfResponse;
        this.closeStatus = mySurvey.getCloseStatus() == CloseStatus.ACTIVE ? true : false;
        this.surveyGenderPercent = GenderRatioDto.calculateRatio(respondents, numberOfResponse);
        this.surveyAgePercent = AgeRatioDto.calculateRatio(respondents, numberOfResponse);
        this.questionsResult = mySurvey.getQuestions().stream()
                .map(question -> QuestionStatisticsDto.from(
                        question,
                        allResponses)
                )
                .collect(Collectors.toList());
    }

    // 테스트용
    public FindSurveyStatisticsResponseDto(Long surveyId,
                                           String surveyTitle,
                                           String surveyDate,
                                           Integer numberOfResponse,
                                           Boolean closeStatus,
                                           List<GenderRatioDto> surveyGenderPercent,
                                           List<AgeRatioDto> surveyAgePercent,
                                           List<QuestionStatisticsDto> questionsResult) {
        this.surveyId = surveyId;
        this.surveyTitle = surveyTitle;
        this.surveyDate = surveyDate;
        this.numberOfResponse = numberOfResponse;
        this.closeStatus = closeStatus;
        this.surveyGenderPercent = surveyGenderPercent;
        this.surveyAgePercent = surveyAgePercent;
        this.questionsResult = questionsResult;
    }

    public String changeDateFormat(Survey mySurvey) {
        String date = Arrays.stream(mySurvey.getCreatedAt().split(" "))  // 날짜 변환 2020-1-1
                .limit(3).collect(Collectors.joining("-")).replace(".", "");

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yy-M-d");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate inputDate = LocalDate.parse(date, inputFormatter);

        return inputDate.format(outputFormatter);
    }
}
