package com.kakaobean.unit.controller.response;

import com.kakaobean.core.response.application.dto.request.RegisterSurveyResponseRequestDto;
import com.kakaobean.core.response.application.dto.response.FindResponsesDto;
import com.kakaobean.core.response.application.dto.response.RegisterSurveyResponseSubmmitDto;
import com.kakaobean.core.response.application.dto.response.SurveyResponseDto;
import com.kakaobean.core.survey.application.dto.response.FindSurveyResponseDto;
import com.kakaobean.response.dto.request.RegisterSurveyResponseRequest;
import com.kakaobean.unit.controller.ControllerTest;
import com.kakaobean.unit.controller.factory.response.request.RegisterSurveyResponseRequestFactory;
import com.kakaobean.unit.controller.security.WithMockUser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static com.kakaobean.docs.SpringRestDocsUtils.getDocumentRequest;
import static com.kakaobean.docs.SpringRestDocsUtils.getDocumentResponse;
import static com.kakaobean.docs.extractor.response.request.SurveyResponsePayloadSubsectionExtractorFactory.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class ResponseControllerTest extends ControllerTest {

    @WithMockUser
    @Test
    @DisplayName("응답 생성 API 명세서 테스트")
    public void registerResponse() throws Exception{

        // given
        RegisterSurveyResponseRequest request = RegisterSurveyResponseRequestFactory.createSuccessSurveyResponseRequest();
        String requestBody = this.objectMapper.writeValueAsString(request);
        given(responseService.registerSurveyResponse(Mockito.any(RegisterSurveyResponseRequestDto.class)))
                .willReturn(new RegisterSurveyResponseSubmmitDto(2L));

        // when
        ResultActions perform = mockMvc.perform(post("/responses")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestBody)
        );

        // then
        perform.andDo(print());
        perform.andExpect(status().is2xxSuccessful());
        createRegisterSurveyResponseSnippet(perform);
        createRegisterRangeBarQuestionResponseSnippet(perform);
        createRegisterEssayQuestionResponseSnippet(perform);
        createRegisterMultipleChoiceQuestionResponseSnippet(perform);

    }

    private void createRegisterSurveyResponseSnippet(ResultActions perform) throws Exception {
        perform.andDo(document("register_survey_response",
                getDocumentRequest(),
                getDocumentResponse(),
                requestFields(
                        getSurveyResponseExtractor(),
                        fieldWithPath("surveyId").type(NUMBER).description("설문 ID"),
                        fieldWithPath("questions").type(ARRAY).description("응답 리스트")
                ),
                responseFields(
                        fieldWithPath("surveyResponseId").type(NUMBER).description("등록한 응답 id")
                )
        ));
    }

    private void createRegisterEssayQuestionResponseSnippet(ResultActions perform) throws Exception {
        perform.andDo(document("register_essay_question_response",
                getDocumentRequest(),
                getDocumentResponse(),
                requestFields(
                        getEssayQuestionResponseExtractor(),
                        fieldWithPath("type").type(STRING).description("질문 타입"),
                        fieldWithPath("questionId").type(NUMBER).description("질문 ID"),
                        fieldWithPath("answers").type(STRING).description("단답형 질문 답변")
                )
        ));
    }

    private void createRegisterRangeBarQuestionResponseSnippet(ResultActions perform) throws Exception {
        perform.andDo(document("register_range_bar_question_response",
                getDocumentRequest(),
                getDocumentResponse(),
                requestFields(
                        getRangeQuestionResponseExtractor(),
                        fieldWithPath("type").type(STRING).description("질문 타입"),
                        fieldWithPath("questionId").type(NUMBER).description("질문 ID"),
                        fieldWithPath("answers").type(NUMBER).description("범위 선택 값")
                )
        ));
    }

    private void createRegisterMultipleChoiceQuestionResponseSnippet(ResultActions perform) throws Exception {
        perform.andDo(document("register_multiple_choice_question_response",
                getDocumentRequest(),
                getDocumentResponse(),
                requestFields(
                        getMultipleChoiceQuestionResponseExtractor(),
                        fieldWithPath("type").type(STRING).description("질문 타입"),
                        fieldWithPath("questionId").type(NUMBER).description("질문 ID"),
                        fieldWithPath("answers").type(ARRAY).description("객관식에서 선택한 답변 배열"),
                        fieldWithPath("answers[].answerId").description("객관식에서 선택한 답변의 ID"),
                        fieldWithPath("answers[].content").description("객관식에서 선택한 답변의 내용")
                )
        ));
    }
}
