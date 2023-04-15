package com.kakaobean.core.survey.application.dto;

import com.kakaobean.core.survey.application.dto.question.RegisterQuestionRequestDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

@Getter
public class RegisterSurveyRequestDto {

    private Long memberId;
    private List<RegisterQuestionRequestDto> dtoList = new ArrayList<>();

    public RegisterSurveyRequestDto(Long memberId, List<RegisterQuestionRequestDto> dtoList) {
        this.memberId = memberId;
        this.dtoList = dtoList;
    }
}
