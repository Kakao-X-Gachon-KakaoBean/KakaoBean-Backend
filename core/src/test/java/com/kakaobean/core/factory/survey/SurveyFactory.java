package com.kakaobean.core.factory.survey;

import com.kakaobean.core.survey.domain.Survey;
import com.kakaobean.core.survey.domain.SurveyOwner;

import java.util.List;

public class SurveyFactory {

    private SurveyFactory(){}

    public static Survey createWithId(){
        return Survey.builder().id(1L).build();
    }

    public static Survey createSurvey(){
        return Survey.builder()
                .surveyOwner(new SurveyOwner(1L))
                .questions(List.of())
                .title("설문 제목")
                .build();
    }
}
