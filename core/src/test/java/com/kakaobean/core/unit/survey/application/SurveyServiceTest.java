package com.kakaobean.core.unit.survey.application;

import com.kakaobean.core.factory.survey.RegisterSurveyServiceDtoFactory;
import com.kakaobean.core.factory.survey.SurveyFactory;
import com.kakaobean.core.survey.application.SurveyMapper;
import com.kakaobean.core.survey.application.SurveyService;
import com.kakaobean.core.survey.application.dto.RegisterSurveyRequestDto;
import com.kakaobean.core.survey.application.dto.RegisterSurveyResponseDto;
import com.kakaobean.core.survey.domain.Survey;
import com.kakaobean.core.survey.domain.SurveyRepository;
import com.kakaobean.core.survey.domain.SurveyValidator;
import com.kakaobean.core.survey.exception.NoMatchingQuestionAnswerException;
import com.kakaobean.core.survey.exception.NoMatchingQuestionNumberException;
import com.kakaobean.core.unit.UnitTest;
import org.assertj.core.api.AbstractThrowableAssert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SurveyServiceTest extends UnitTest {

    SurveyService surveyService;

    @Mock
    SurveyRepository surveyRepository;

    @BeforeEach
    void beforeEach(){
        surveyService = new SurveyService(surveyRepository, new SurveyMapper(), new SurveyValidator());
    }

    @DisplayName("설문 등록을 성공한다.")
    @Test
    void successRegisterSurvey(){
        //given
        RegisterSurveyRequestDto dto = RegisterSurveyServiceDtoFactory.createSuccessCase1Request();
        Survey survey = SurveyFactory.create();
        BDDMockito.given(surveyRepository.save(Mockito.any())).willReturn(survey);

        //when
        RegisterSurveyResponseDto res = surveyService.registerSurvey(dto);

        //then
        Assertions.assertThat(res.getSurveyId()).isEqualTo(survey.getId());
    }

    @DisplayName("분기점에서 다음 질문 번호를 없는 것으로 넣었다. 이후에 설문 등록을 실패한다.")
    @Test
    void registerFailCase1Survey(){

        //given
        RegisterSurveyRequestDto dto = RegisterSurveyServiceDtoFactory.createFailCase1Request();

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
        RegisterSurveyRequestDto dto = RegisterSurveyServiceDtoFactory.createFailCase2Request();

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
        RegisterSurveyRequestDto dto = RegisterSurveyServiceDtoFactory.createFailCase3Request();

        //when
        AbstractThrowableAssert<?, ? extends Throwable> result = assertThatThrownBy(() -> {
            surveyService.registerSurvey(dto);
        });

        //then
        result.isInstanceOf(NoMatchingQuestionNumberException.class);
        result.hasMessage("9번 질문에 해당하는 번호가 없습니다.");
    }

}
