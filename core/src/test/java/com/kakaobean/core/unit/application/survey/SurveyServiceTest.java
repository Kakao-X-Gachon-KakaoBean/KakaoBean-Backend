package com.kakaobean.core.unit.application.survey;

import com.kakaobean.core.common.domain.BaseStatus;
import com.kakaobean.core.common.event.Events;
import com.kakaobean.core.factory.survey.RegisterSurveyServiceDtoFactory;
import com.kakaobean.core.factory.survey.SurveyFactory;
import com.kakaobean.core.survey.application.SurveyMapper;
import com.kakaobean.core.survey.application.SurveyService;
import com.kakaobean.core.survey.application.dto.request.RegisterSurveyRequestDto;
import com.kakaobean.core.survey.application.dto.response.RegisterSurveyResponseDto;
import com.kakaobean.core.survey.application.dto.response.RemoveSurveyResponseDto;
import com.kakaobean.core.survey.domain.Survey;
import com.kakaobean.core.survey.domain.SurveyRepository;
import com.kakaobean.core.survey.domain.SurveyValidator;
import com.kakaobean.core.survey.domain.event.RemovedSurveyEvent;
import com.kakaobean.core.survey.exception.NoMatchingQuestionAnswerException;
import com.kakaobean.core.survey.exception.NoMatchingQuestionNumberException;
import com.kakaobean.core.unit.UnitTest;
import org.assertj.core.api.AbstractThrowableAssert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.Optional;

import static com.kakaobean.core.common.domain.BaseStatus.*;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.*;

public class SurveyServiceTest extends UnitTest {

    SurveyService surveyService;

    @Mock
    SurveyRepository surveyRepository;

    MockedStatic<Events> mockedEvents;

    @BeforeEach
    void beforeEach(){
        surveyService = new SurveyService(surveyRepository, new SurveyMapper(), new SurveyValidator());
        mockedEvents = mockStatic(Events.class);
    }

    @AfterEach
    void afterEach(){
        mockedEvents.close();
    }

    @DisplayName("설문 등록을 성공한다.")
    @Test
    void successRegisterSurvey(){
        //given
        RegisterSurveyRequestDto dto = RegisterSurveyServiceDtoFactory.createSuccessCase1Request();
        Survey survey = SurveyFactory.createWithId();
        given(surveyRepository.save(Mockito.any())).willReturn(survey);

        //when
        RegisterSurveyResponseDto res = surveyService.registerSurvey(dto);

        //then
        assertThat(res.getSurveyId()).isEqualTo(survey.getId());
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

    @DisplayName("설문을 삭제한다.")
    @Test
    void removeSurvey(){

        //given
        Survey survey = SurveyFactory.createWithId();
        given(surveyRepository.findSurveyBySurveyIdAndOwnerId(Mockito.anyLong(), Mockito.anyLong()))
                .willReturn(Optional.of(survey));

        //when
        surveyService.removeSurvey(1L, 1L);

        //then
        assertThat(survey.getStatus()).isSameAs(INACTIVE);
        mockedEvents.verify(() -> Events.raise(Mockito.any(RemovedSurveyEvent.class)), times(1));
    }
}
