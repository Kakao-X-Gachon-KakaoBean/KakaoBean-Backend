package com.kakaobean.unit.controller.survey;


import com.kakaobean.core.survey.application.dto.request.RegisterSurveyRequestDto;
import com.kakaobean.core.survey.application.dto.response.RegisterSurveyResponseDto;
import com.kakaobean.survey.dto.request.RegisterSurveyRequest;
import com.kakaobean.unit.controller.ControllerTest;
import com.kakaobean.unit.controller.factory.survey.RegisterSurveyRequestFactory;
import com.kakaobean.unit.controller.security.WithMockUser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static com.kakaobean.docs.SpringRestDocsUtils.getDocumentRequest;
import static com.kakaobean.docs.SpringRestDocsUtils.getDocumentResponse;
import static com.kakaobean.unit.controller.survey.CustomPayloadSubsectionExtractorFactory.*;

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
        String requestBody = this.objectMapper.writeValueAsString(request);
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
        createRegisterRangeBarQuestionSnippet(perform);
        createRegisterEssayQuestionSnippet(perform);
        createRegisterMultipleChoiceQuestionSnippet(perform);
    }

    private void createRegisterRangeBarQuestionSnippet(ResultActions perform) throws Exception {
        perform.andDo(document("register_range_bar_question",
                getDocumentRequest(),
                getDocumentResponse(),
                requestFields(
                        getRangeQuestionExtractor(),
                        fieldWithPath("type").type(STRING).description("질문 타입"),
                        fieldWithPath("title").type(STRING).description("질문 제목"),
                        fieldWithPath("explanation").type(STRING).description("질문 설명"),
                        fieldWithPath("questionNumber").type(STRING).description("질문 번호"),
                        fieldWithPath("min").type(NUMBER).description("Ragne 답변 최솟값"),
                        fieldWithPath("max").type(NUMBER).description("Range 답변 최댓값"),
                        fieldWithPath("finalQuestion").type(BOOLEAN).description("마지막 질문인가"),
                        fieldWithPath("nextQuestionNumber").type(STRING).description("다음 질문 번호")
                ),
                responseFields(
                        fieldWithPath("surveyId").type(NUMBER).description("등록한 설문 id").ignored()
                )
        ));
    }

    private void createRegisterEssayQuestionSnippet(ResultActions perform) throws Exception {
        perform.andDo(document("register_essay_question",
                getDocumentRequest(),
                getDocumentResponse(),
                requestFields(
                        getEssayQuestionExtractor(),
                        fieldWithPath("type").type(STRING).description("질문 타입"),
                        fieldWithPath("title").type(STRING).description("질문 제목"),
                        fieldWithPath("explanation").type(STRING).description("질문 설명"),
                        fieldWithPath("questionNumber").type(STRING).description("질문 번호"),
                        fieldWithPath("finalQuestion").type(BOOLEAN).description("마지막 질문인가"),
                        fieldWithPath("nextQuestionNumber").type(STRING).description("다음 질문 번호")
                ),
                responseFields(
                        fieldWithPath("surveyId").type(NUMBER).description("등록한 설문 id")
                )
        ));
    }

    private void createRegisterMultipleChoiceQuestionSnippet(ResultActions perform) throws Exception {
        perform.andDo(document("register_multiple_choice_question",
                getDocumentRequest(),
                getDocumentResponse(),
                requestFields(
                        getMultipleChoiceQuestionExtractor(),
                        fieldWithPath("type").type(STRING).description("질문 타ㅋ"),
                        fieldWithPath("title").type(STRING).description("질문 제목"),
                        fieldWithPath("explanation").type(STRING).description("질문 설명"),
                        fieldWithPath("questionNumber").type(STRING).description("질문 번호"),
                        fieldWithPath("finalQuestion").type(BOOLEAN).description("마지막 질문인가"),
                        fieldWithPath("numberOfAnswerChoices").type(NUMBER).description("객관식에서 선택할 수 있는 질문의 개수"),
                        fieldWithPath("answers").type(ARRAY).description("객관식 답변 배열"),
                        fieldWithPath("nextQuestionNumber").type(STRING).description("다음 질문 번호"),
                        fieldWithPath("logics").type(ARRAY).description("객관식에 설정할 다음 답변 이동 분기 로직"),
                        fieldWithPath("logics[].conditionOfQuestionAnswers").type(ARRAY).description("분기 로직에서 이동의 조건이 되는 객관식의 답변"),
                        fieldWithPath("logics[].nextQuestionNumber").type(STRING).description("분기 로직에서 이동할 다음 질문 번호")),
                responseFields(
                        fieldWithPath("surveyId").type(NUMBER).description("등록한 설문 id")
                )
        ));
    }



    @Test
    @WithMockUser
    @DisplayName("설문 등록 API 명세서 테스트.")
    void showSurveyTest(){

    }
}


//https://stackoverflow.com/questions/61637182/how-to-document-a-mixed-typed-array-structures-in-requests-responses-with-spring
//PayloadSubsectionExtractor.
//https://www.tabnine.com/code/java/methods/org.springframework.restdocs.payload.SubsectionDescriptor/description
//https://stackoverflow.com/questions/32865427/document-hierarchical-json-payload-with-spring-rest-docs