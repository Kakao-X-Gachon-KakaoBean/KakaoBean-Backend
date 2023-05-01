package com.kakaobean.core.survey.application.dto.request;

import com.kakaobean.core.survey.application.dto.response.question.RegisterQuestionRequestDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class RegisterSurveyRequestDto {

    private Long memberId;
    private String surveyTitle;
    private List<RegisterQuestionRequestDto> dtoList = new ArrayList<>();

    public RegisterSurveyRequestDto(String surveyTitle, Long memberId, List<RegisterQuestionRequestDto> dtoList) {
        this.memberId = memberId;
        this.dtoList = dtoList;
        this.surveyTitle = surveyTitle;
    }
}
