package com.kakaobean.core.factory.survey;

import com.kakaobean.core.survey.domain.Survey;

public class SurveyFactory {

    private SurveyFactory(){}

    public static Survey create(){
        return Survey.builder().id(1L).build();
    }
}
