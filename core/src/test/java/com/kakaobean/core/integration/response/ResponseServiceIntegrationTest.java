package com.kakaobean.core.integration.response;

import com.kakaobean.core.integration.IntegrationTest;
import com.kakaobean.core.response.application.ResponseService;
import com.kakaobean.core.response.application.dto.request.RegisterSurveyResponseRequestDto;
import com.kakaobean.core.response.application.dto.response.RegisterSurveyResponseSubmmitDto;
import com.kakaobean.core.survey.application.SurveyService;
import com.kakaobean.core.survey.application.dto.request.RegisterSurveyRequestDto;
import com.kakaobean.core.survey.application.dto.response.RegisterSurveyResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.kakaobean.core.factory.response.RegisterSurveyResponseServiceDtoFactory.*;
import static com.kakaobean.core.factory.survey.RegisterSurveyServiceDtoFactory.createSuccessCase1Request;
import static org.assertj.core.api.Assertions.*;

public class ResponseServiceIntegrationTest extends IntegrationTest {

    @Autowired
    ResponseService responseService;

    @Autowired
    SurveyService surveyService;

    @DisplayName("설문조사에 대한 응답 제출에 성공한다.")
    @Test
    void registerSurveyResponse(){

        // given
        RegisterSurveyRequestDto testSurveyDto = createSuccessCase1Request();
        RegisterSurveyResponseDto testSurvey = surveyService.registerSurvey(testSurveyDto);
        RegisterSurveyResponseRequestDto dto = createSuccessSurveyResponseCase1Request(testSurvey.getSurveyId());

        // when
        RegisterSurveyResponseSubmmitDto result = responseService.registerSurveyResponse(dto);

        // then
        assertThat(result.getSurveyResponseId()).isNotNull();

    }



}
