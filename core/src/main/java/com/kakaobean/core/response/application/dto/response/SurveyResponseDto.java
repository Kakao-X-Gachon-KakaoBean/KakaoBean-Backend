package com.kakaobean.core.response.application.dto.response;

import com.kakaobean.core.member.domain.Gender;
import com.kakaobean.core.response.application.dto.response.question.QuestionResponseDto;
import com.kakaobean.core.response.infrastructure.QuerydslResponsesDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class SurveyResponseDto {

    private Gender gender;
    private Integer age;
    private String email;
    private String name;
    private List<QuestionResponseDto> questionResponses = new ArrayList<>();

    @Builder
    public SurveyResponseDto(Gender gender,
                             Integer age,
                             String email,
                             String name,
                             List<QuestionResponseDto> questionResponses) {
        this.gender = gender;
        this.age = age;
        this.email = email;
        this.name = name;
        this.questionResponses = questionResponses;
    }

    public static SurveyResponseDto from(QuerydslResponsesDto dto) {
        return SurveyResponseDto
                .builder()
                .gender(dto.getGender())
                .age(dto.getAge())
                .email(dto.getEmail())
                .name(dto.getName())
                .questionResponses(dto
                        .getSurveyResponse()
                        .getQuestionResponses()
                        .stream()
                        .map(QuestionResponseDto::from)
                        .collect(Collectors.toList()))
                .build();
    }
}
