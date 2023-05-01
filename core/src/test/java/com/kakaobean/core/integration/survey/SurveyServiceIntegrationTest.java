package com.kakaobean.core.integration.survey;

import com.kakaobean.core.factory.survey.RegisterSurveyServiceDtoFactory;
import com.kakaobean.core.integration.IntegrationTest;
import com.kakaobean.core.survey.application.SurveyProvider;
import com.kakaobean.core.survey.application.SurveyService;
import com.kakaobean.core.survey.application.dto.request.RegisterSurveyRequestDto;
import com.kakaobean.core.survey.application.dto.response.GetSurveyResponseDto;
import com.kakaobean.core.survey.application.dto.response.RegisterSurveyResponseDto;

import com.kakaobean.core.survey.exception.NoMatchingQuestionAnswerException;
import com.kakaobean.core.survey.exception.NoMatchingQuestionNumberException;
import com.kakaobean.core.survey.exception.NotExistsSurveyException;
import org.assertj.core.api.AbstractThrowableAssert;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import static com.kakaobean.core.factory.survey.RegisterSurveyServiceDtoFactory.*;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SurveyServiceIntegrationTest extends IntegrationTest {

    @Autowired
    SurveyService surveyService;

    @Autowired
    SurveyProvider surveyProvider;

    @DisplayName("설문 등록을 성공한다.")
    @Test
    void registerSurvey(){

        //given
        RegisterSurveyRequestDto dto = createSuccessCase1Request();

        //when
        RegisterSurveyResponseDto result = surveyService.registerSurvey(dto);

        //then
        assertThat(result.getSurveyId()).isNotNull();
    }

    @DisplayName("분기점에서 다음 질문 번호를 없는 것으로 넣었다. 이후에 설문 등록을 실패한다.")
    @Test
    void registerFailCase1Survey(){

        //given
        RegisterSurveyRequestDto dto = createFailCase1Request();

        //when
        AbstractThrowableAssert<?, ? extends Throwable> result = assertThatThrownBy(() -> {
            surveyService.registerSurvey(dto);
        });

        //then
        result.isInstanceOf(NoMatchingQuestionNumberException.class);
        result.hasMessage("9번 질문에 해당하는 번호가 없습니다.");
    }

    @DisplayName("분기점에서 조건으로 없는 답변을 넣었다. 이후에 설문 등록을 실패한다.")
    @Test
    void registerFailCase2Survey(){

        //given
        RegisterSurveyRequestDto dto = createFailCase2Request();

        //when
        AbstractThrowableAssert<?, ? extends Throwable> result = assertThatThrownBy(() -> {
            surveyService.registerSurvey(dto);
        });

        //then
        result.isInstanceOf(NoMatchingQuestionAnswerException.class);
        result.hasMessage("'없는 답 예시' 내용의 답변이 없습니다.");
    }

    @DisplayName("분기점에서 다음 질문으로 없는 질문 번호를 넣었다. 이후에 설문 등록을 실패한다.")
    @Test
    void registerFailCase3Survey(){

        //given
        RegisterSurveyRequestDto dto = createFailCase3Request();

        //when
        AbstractThrowableAssert<?, ? extends Throwable> result = assertThatThrownBy(() -> {
            surveyService.registerSurvey(dto);
        });

        //then
        result.isInstanceOf(NoMatchingQuestionNumberException.class);
        result.hasMessage("9번 질문에 해당하는 번호가 없습니다.");
    }

    @Test
    @DisplayName("설문 조회를 성공한다.")
    void successGetSurvey(){

        //given
        RegisterSurveyRequestDto dto = createSuccessCase1Request();
        RegisterSurveyResponseDto res = surveyService.registerSurvey(dto);

        //when
        GetSurveyResponseDto result = surveyProvider.getSurvey(res.getSurveyId());

        //then
        assertThat(result.getSurveyTitle()).isEqualTo("title");
        assertThat(result.getSurveyId()).isEqualTo(res.getSurveyId());

    }

    @Test
    @DisplayName("설문 조회를 실패한다.")
    void failGetSurvey(){

        //given
        RegisterSurveyRequestDto dto = createSuccessCase1Request();
        RegisterSurveyResponseDto res = surveyService.registerSurvey(dto);

        //when
        AbstractThrowableAssert<?, ? extends Throwable> result = assertThatThrownBy(() -> {
            surveyProvider.getSurvey(33L);
        });

        //then
        result.isInstanceOf(NotExistsSurveyException.class);
        result.hasMessage("존재하지 않는 설문입니다.");
    }
}
