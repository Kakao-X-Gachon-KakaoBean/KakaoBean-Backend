package com.kakaobean.core.integration.survey;

import com.kakaobean.core.integration.IntegrationTest;
import com.kakaobean.core.response.application.ResponseService;
import com.kakaobean.core.response.application.dto.request.RegisterSurveyResponseRequestDto;
import com.kakaobean.core.response.application.dto.response.RegisterSurveyResponseSubmmitDto;
import com.kakaobean.core.survey.application.SurveyProvider;
import com.kakaobean.core.survey.application.SurveyService;
import com.kakaobean.core.survey.application.dto.request.RegisterSurveyRequestDto;
import com.kakaobean.core.survey.application.dto.response.FindOwnSurveyListResponseDto;
import com.kakaobean.core.survey.application.dto.response.FindSubmittedSurveyListResponseDto;
import com.kakaobean.core.survey.application.dto.response.FindSurveyResponseDto;
import com.kakaobean.core.survey.application.dto.response.RegisterSurveyResponseDto;
import com.kakaobean.core.survey.domain.CloseStatus;
import com.kakaobean.core.survey.domain.SurveyRepository;
import com.kakaobean.core.survey.exception.*;
import com.kakaobean.core.survey.exception.questionflowlogic.ExistSameConditionException;
import com.kakaobean.core.survey.exception.questionflowlogic.ExistSameLogicException;
import org.assertj.core.api.AbstractThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

import static com.kakaobean.core.common.domain.BaseStatus.INACTIVE;
import static com.kakaobean.core.factory.response.RegisterSurveyResponseServiceDtoFactory.createSuccessSurveyResponseCase1Request;
import static com.kakaobean.core.factory.survey.RegisterSurveyServiceDtoFactory.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SurveyServiceIntegrationTest extends IntegrationTest {

    @Autowired
    SurveyService surveyService;

    @Autowired
    SurveyProvider surveyProvider;

    @Autowired
    ResponseService responseService;

    @Autowired
    SurveyRepository surveyRepository;

    @Autowired
    EntityManager em;

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

    @DisplayName("Range 질문의 min과 max 범위를 동일하 설정하였다. 이후에 설문 등록을 실패한다.")
    @Test
    void registerFailCase4Survey(){

        //given
        RegisterSurveyRequestDto dto = createFailCase5Request();

        //when
        AbstractThrowableAssert<?, ? extends Throwable> result = assertThatThrownBy(() -> {
            surveyService.registerSurvey(dto);
        });

        //then
        result.isInstanceOf(RangeQuestionBoundaryValueException.class);
        result.hasMessage("Range 질문의 최솟값과 최댓값이 동일합니다.");
    }

    @DisplayName("분기문에서 동일한 조건을 가진 로직을 2개 이상 포함하였다. 이후에 설문 등록을 실패한다.")
    @Test
    void registerFailCase5Survey(){

        //given
        RegisterSurveyRequestDto dto = createFailCase6Request();

        //when
        AbstractThrowableAssert<?, ? extends Throwable> result = assertThatThrownBy(() -> {
            surveyService.registerSurvey(dto);
        });

        //then
        result.isInstanceOf(ExistSameConditionException.class);
        result.hasMessage("동일한 조건을 가진 로직이 2개 이상 존재합니다.");
    }

    @DisplayName("분기문에서 동일한 조건을 가진 로직을 2개 이상 포함하면서 이동하는 질문의 번호도 같다. 이후에 설문 등록을 실패한다.")
    @Test
    void registerFailCase6Survey(){

        //given
        RegisterSurveyRequestDto dto = createFailCase7Request();

        //when
        AbstractThrowableAssert<?, ? extends Throwable> result = assertThatThrownBy(() -> {
            surveyService.registerSurvey(dto);
        });

        //then
        result.isInstanceOf(ExistSameLogicException.class);
        result.hasMessage("동일한 로직이 존재합니다.");
    }

    @DisplayName("중복되는 질문 번호가 있다. 이후에 설문 등록을 실패한다.")
    @Test
    void registerFailCase7Survey(){

        //given
        RegisterSurveyRequestDto dto = createFailCase8Request();

        //when
        AbstractThrowableAssert<?, ? extends Throwable> result = assertThatThrownBy(() -> {
            surveyService.registerSurvey(dto);
        });

        //then
        result.isInstanceOf(SameQuestionNumberException.class);
        result.hasMessage("중복되는 질문 번호가 있습니다.");
    }

    @Test
    @DisplayName("설문 조회를 성공한다.")
    void successGetSurvey(){

        //given
        RegisterSurveyRequestDto dto = createSuccessCase1Request();
        RegisterSurveyResponseDto res = surveyService.registerSurvey(dto);

        //when
        FindSurveyResponseDto result = surveyProvider.getSurvey(res.getSurveyId(), dto.getMemberId());

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
            surveyProvider.getSurvey(33L, dto.getMemberId());
        });

        //then
        result.isInstanceOf(NotExistsSurveyException.class);
        result.hasMessage("존재하지 않는 설문입니다.");
    }

    @Test
    @DisplayName("마감된 설문을 다른 이용자가 조회하려하면 조회를 실패한다.")
    void failGetClosedSurvey(){

        //given
        RegisterSurveyRequestDto dto = createSuccessCase1Request();
        RegisterSurveyResponseDto res = surveyService.registerSurvey(dto);

        surveyService.closeSurvey(dto.getMemberId(), res.getSurveyId()); // 설문 주인이 설문을 마감한다.

        //when
        AbstractThrowableAssert<?, ? extends Throwable> result = assertThatThrownBy(() -> {
            surveyProvider.getSurvey(res.getSurveyId(), 100L); // 다른 이용자가 설문을 조회하려고 한다
        });

        //then
        result.isInstanceOf(ClosedSurveyException.class);
        result.hasMessage("참여 마감된 설문입니다.");
    }

    @Test
    @DisplayName("내가 만든 설문 조회를 성공한다.")
    void successGetOwnSurvey(){
        //given
        //설문 생성
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

    @Test
    @DisplayName("내가 참여한 설문 조회를 성공한다.")
    void successGetSubmittedSurvey(){
        //given
        //설문 생성
        RegisterSurveyRequestDto dto1 = createSuccessCase1Request();
        RegisterSurveyResponseDto mySurvey1 = surveyService.registerSurvey(dto1);
        RegisterSurveyRequestDto dto2 = createSuccessCase1Request();
        RegisterSurveyResponseDto mySurvey2 = surveyService.registerSurvey(dto2);

        //응답 생성
        RegisterSurveyResponseRequestDto responseDto1 = createSuccessSurveyResponseCase1Request(mySurvey1.getSurveyId());
        RegisterSurveyResponseSubmmitDto mySurveyResponse1 = responseService.registerSurveyResponse(responseDto1);
        RegisterSurveyResponseRequestDto responseDto2 = createSuccessSurveyResponseCase1Request(mySurvey2.getSurveyId());
        RegisterSurveyResponseSubmmitDto mySurveyResponse2 = responseService.registerSurveyResponse(responseDto2);

        //when
        FindSubmittedSurveyListResponseDto result = surveyProvider.getSubmittedSurvey(1L);

        //then
        assertThat(result.getMySubmittedSurveys().size()).isEqualTo(2);
    }


    @DisplayName("설문을 삭제한다.")
    @Test
    void removeSurvey(){

        //given
        RegisterSurveyRequestDto dto = createSuccessCase1Request();
        RegisterSurveyResponseDto result = surveyService.registerSurvey(dto);

        //when
        surveyService.removeSurvey(1L, result.getSurveyId());

        //then
        assertThat(surveyRepository.findById(result.getSurveyId()).get().getStatus()).isSameAs(INACTIVE);
    }

    @DisplayName("설문을 마감한다.")
    @Test
    void closeSurvey(){
        //given
        RegisterSurveyRequestDto dto = createSuccessCase1Request();
        RegisterSurveyResponseDto result = surveyService.registerSurvey(dto);

        //when
        surveyService.closeSurvey(1L, result.getSurveyId());

        //then
        assertThat(surveyRepository.findById(result.getSurveyId()).get().getCloseStatus())
                .isSameAs(CloseStatus.ACTIVE);

    }
}
