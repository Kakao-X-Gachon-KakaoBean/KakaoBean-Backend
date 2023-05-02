package com.kakaobean.core.unit.domain.survey;

import com.kakaobean.core.factory.survey.RegisterSurveyServiceDtoFactory;
import com.kakaobean.core.survey.application.SurveyMapper;
import com.kakaobean.core.survey.application.dto.request.RegisterSurveyRequestDto;
import com.kakaobean.core.survey.domain.Survey;
import com.kakaobean.core.survey.domain.SurveyValidator;
import com.kakaobean.core.survey.exception.SameQuestionNumberException;
import com.kakaobean.core.unit.UnitTest;

import org.assertj.core.api.AbstractThrowableAssert;
import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class SurveyTest extends UnitTest {

    private SurveyMapper surveyMapper;

    @BeforeEach
    void beforeEach(){
        surveyMapper = new SurveyMapper();
    }

    @Test
    void successValidationSurvey(){
        //given
        RegisterSurveyRequestDto dto = RegisterSurveyServiceDtoFactory.createSuccessCase1Request();
        Survey survey = surveyMapper.mapFrom(dto);
        SurveyValidator surveyValidator = new SurveyValidator();

        //when, then
        survey.place(surveyValidator);
    }


    @Test
    @DisplayName("겹치는 질문 번호를 넣었다.")
    void failValidationSurveyCase1(){
        //given
        RegisterSurveyRequestDto dto = RegisterSurveyServiceDtoFactory.createFailCase4Request();
        Survey survey = surveyMapper.mapFrom(dto);
        SurveyValidator surveyValidator = new SurveyValidator();

        //when
        AbstractThrowableAssert<?, ? extends Throwable> result = Assertions.assertThatThrownBy(() -> {
            survey.place(surveyValidator);
        });

        //then
        result.isInstanceOf(SameQuestionNumberException.class);
        result.hasMessage("중복되는 질문 번호가 있습니다.");
    }
}
