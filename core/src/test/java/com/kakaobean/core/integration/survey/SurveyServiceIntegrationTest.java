package com.kakaobean.core.integration.survey;

import com.kakaobean.core.integration.IntegrationTest;
import com.kakaobean.core.survey.application.SurveyProvider;
import com.kakaobean.core.survey.application.SurveyService;
import com.kakaobean.core.survey.application.dto.request.RegisterSurveyRequestDto;
import com.kakaobean.core.survey.application.dto.response.FindOwnSurveyListResponseDto;
import com.kakaobean.core.survey.application.dto.response.FindSurveyResponseDto;
import com.kakaobean.core.survey.application.dto.response.RegisterSurveyResponseDto;

import com.kakaobean.core.survey.exception.NoMatchingQuestionAnswerException;
import com.kakaobean.core.survey.exception.NoMatchingQuestionNumberException;
import com.kakaobean.core.survey.exception.NotExistsSurveyException;
import com.sun.xml.bind.v2.TODO;
import org.assertj.core.api.AbstractThrowableAssert;

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
        FindSurveyResponseDto result = surveyProvider.getSurvey(res.getSurveyId());

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

    @Test
    @DisplayName("내가 만든 설문 조회를 성공한다.")
    void successGetOwnSurvey(){
        //given
        RegisterSurveyRequestDto dto1 = createSuccessCase1Request();
        RegisterSurveyResponseDto mySurvey1 = surveyService.registerSurvey(dto1);
        RegisterSurveyRequestDto dto2 = createSuccessCase1Request();
        RegisterSurveyResponseDto mySurvey2 = surveyService.registerSurvey(dto2);

        //when
        FindOwnSurveyListResponseDto result = surveyProvider.getOwnSurvey(1L);

        //then
        // 2개 등록 했으니까 조회로 가져온 설문 개수가 2개이면 통과
        assertThat(result.getMyOwnSurveys().size()).isEqualTo(2);
        assertThat(result.getMyOwnSurveys().get(0).getSurveyId()).isEqualTo(mySurvey1.getSurveyId());
        assertThat(result.getMyOwnSurveys().get(1).getSurveyId()).isEqualTo(mySurvey2.getSurveyId());
    }

}
