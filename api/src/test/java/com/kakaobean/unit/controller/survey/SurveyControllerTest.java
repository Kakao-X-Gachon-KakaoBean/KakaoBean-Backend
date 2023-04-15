package com.kakaobean.unit.controller.survey;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.kakaobean.core.member.application.dto.request.RegisterMemberRequestDto;
import com.kakaobean.core.survey.application.dto.RegisterSurveyRequestDto;
import com.kakaobean.core.survey.application.dto.RegisterSurveyResponseDto;
import com.kakaobean.survey.dto.request.RegisterSurveyRequest;
import com.kakaobean.unit.controller.ControllerTest;
import com.kakaobean.unit.controller.factory.member.RegisterMemberRequestFactory;
import com.kakaobean.unit.controller.factory.survey.RegisterSurveyRequestFactory;
import com.kakaobean.unit.controller.factory.survey.question.RegisterQuestionRequestFactory;
import com.kakaobean.unit.controller.security.WithMockUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static com.kakaobean.docs.SpringRestDocsUtils.getDocumentRequest;
import static com.kakaobean.docs.SpringRestDocsUtils.getDocumentResponse;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SurveyControllerTest extends ControllerTest {

    @Test
    @WithMockUser
    @DisplayName("설문 등록 API 명세서 테스트.")
    void registerSurveyTest() throws Exception {

        //given
        RegisterSurveyRequest request = RegisterSurveyRequestFactory.createSuccessCase1Request();
        String requestBody = objectMapper.writeValueAsString(request);
        given(surveyService.registerSurvey(Mockito.any(RegisterSurveyRequestDto.class)))
                .willReturn(new RegisterSurveyResponseDto(1L));

        //when
        ResultActions perform = mockMvc.perform(post("/surveys")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestBody)
        );

        //then
        perform.andDo(print());
        perform.andExpect(status().is2xxSuccessful());
//        perform.andDo(document("register_survey",
//                getDocumentRequest(),
//                getDocumentResponse(),
//                requestFields(
//                        beneathPath()
//                        fieldWithPath("questions").type(ARRAY).description("이름"),
//                        fieldWithPath("age").type(NUMBER).description("나이"),
//                        fieldWithPath("gender").type(STRING).description("성별"),
//                        fieldWithPath("email").type(STRING).description("이메일"),
//                        fieldWithPath("password").type(STRING).description("비밀번호"),
//                        fieldWithPath("checkPassword").type(STRING).description("비밀번호 확인"),
//                        fieldWithPath("birth").type(STRING).description("생일")
//                ),
//                responseFields(
//                        fieldWithPath("surveyId").type(NUMBER).description("등록한 설문 id")
//                )
//        ));

    }
}

//https://stackoverflow.com/questions/61637182/how-to-document-a-mixed-typed-array-structures-in-requests-responses-with-spring

//PayloadSubsectionExtractor.


//https://www.tabnine.com/code/java/methods/org.springframework.restdocs.payload.SubsectionDescriptor/description