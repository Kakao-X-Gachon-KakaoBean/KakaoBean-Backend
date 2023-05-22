package com.kakaobean.core.integration.response;

import com.kakaobean.core.integration.IntegrationTest;
import com.kakaobean.core.response.application.ResponseService;
import com.kakaobean.core.response.application.dto.request.RegisterSurveyResponseRequestDto;
import com.kakaobean.core.response.application.dto.response.RegisterSurveyResponseSubmmitDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.kakaobean.core.factory.response.RegisterSurveyResponseServiceDtoFactory.*;
import static org.assertj.core.api.Assertions.*;

public class ResponseServiceIntegrationTest extends IntegrationTest {

    @Autowired
    ResponseService responseService;

    @DisplayName("설문조사에 대한 응답 제출에 성공한다.")
    @Test
    void registerSurveyResponse(){

        // given
        RegisterSurveyResponseRequestDto dto = createSuccessSurveyResponseCase1Request(2L);

        // when
        RegisterSurveyResponseSubmmitDto result = responseService.registerSurveyResponse(dto);

        // then
        assertThat(result.getSurveyResponseId()).isNotNull();

    }



}
