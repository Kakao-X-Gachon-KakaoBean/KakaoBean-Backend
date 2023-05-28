package com.kakaobean.core.response.infrastructure;

import com.kakaobean.core.member.domain.Gender;
import com.kakaobean.core.response.domain.SurveyResponse;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class QuerydslResponsesDto {

    private Gender gender;
    private Integer age;
    private String email;
    private String name;
    private SurveyResponse surveyResponse;

    public QuerydslResponsesDto(Gender gender,
                                Integer age,
                                String email,
                                String name,
                                SurveyResponse surveyResponse) {
        this.gender = gender;
        this.age = age;
        this.email = email;
        this.name = name;
        this.surveyResponse = surveyResponse;
    }
}
