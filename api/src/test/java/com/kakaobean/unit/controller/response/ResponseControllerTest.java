package com.kakaobean.unit.controller.response;

import com.kakaobean.response.dto.request.RegisterSurveyResponseRequest;
import com.kakaobean.unit.controller.ControllerTest;
import com.kakaobean.unit.controller.factory.response.request.RegisterSurveyResponseRequestFactory;
import com.kakaobean.unit.controller.security.WithMockUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class ResponseControllerTest extends ControllerTest {

    @WithMockUser
    @Test
    @DisplayName("응답 생성 API 명세서 테스트")
    public void registerResponse() throws Exception{

        // given
        RegisterSurveyResponseRequest request = RegisterSurveyResponseRequestFactory.createSuccessSurveyResponseRequest();

        // when

        // then

    }

}
