package com.kakaobean.unit.controller.survey;

import com.kakaobean.core.survey.application.dto.response.FindOwnSurveyListResponseDto;
import com.kakaobean.unit.controller.ControllerTest;
import com.kakaobean.unit.controller.factory.survey.response.FindOwnSurveyListResponseFactory;
import com.kakaobean.unit.controller.factory.survey.response.FindSurveyResponseFactory;
import com.kakaobean.unit.controller.security.WithMockUser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static com.kakaobean.docs.SpringRestDocsUtils.getDocumentRequest;
import static com.kakaobean.docs.SpringRestDocsUtils.getDocumentResponse;

import static com.kakaobean.docs.extractor.survey.response.SurveyResponsePayloadSubsectionExtractorFactory.*;
import static org.mockito.BDDMockito.given;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SurveyProviderTest extends ControllerTest {

    @Test
    @WithMockUser
    @DisplayName("내가 만든 설문 조회 API 명세서 테스트.")
    void findOwnSurveyTest() throws Exception{
        // given
        given(surveyProvider.getOwnSurvey(Mockito.any(Long.class))).willReturn(FindOwnSurveyListResponseFactory.create());

        // when
        ResultActions perform = mockMvc.perform((get("/surveys/own-survey"))
                .accept(MediaType.APPLICATION_JSON)
        );

        // then
        perform.andDo(print());
        perform.andExpect(status().is2xxSuccessful());

        perform.andDo(document("find_own_survey",
                getDocumentRequest(),
                getDocumentResponse(),
                responseFields(
                        fieldWithPath("myOwnSurveys").type(ARRAY).description("내가 만든 설문 리스트"),
                        fieldWithPath("myOwnSurveys[].surveyId").type(NUMBER).description("설문 아이디"),
                        fieldWithPath("myOwnSurveys[].surveyTitle").type(STRING).description("설문 제목"),
                        fieldWithPath("myOwnSurveys[].numberOfResponse").type(NUMBER).description("설문을 제출한 응답 수")
                ))
        );

    }

    @Test
    @WithMockUser
    @DisplayName("설문 조회 API 명세서 테스트.")
    void findSurveyTest() throws Exception {
        //given
        given(surveyProvider.getSurvey(Mockito.any(Long.class))).willReturn(FindSurveyResponseFactory.create());

        //when
        ResultActions perform = mockMvc.perform(get("/surveys/{surveyId}", 1L)
                .accept(MediaType.APPLICATION_JSON)
        );

        //then
        perform.andDo(print());
        perform.andExpect(status().is2xxSuccessful());

        createFindSurveySnippet(perform);
        createFindRangeBarQuestionSnippet(perform);
        createFindEssayQuestionSnippet(perform);
        createFindMultipleChoiceQuestionSnippet(perform);
    }

    private void createFindSurveySnippet(ResultActions perform) throws Exception {
        perform.andDo(document("find_survey",
                getDocumentRequest(),
                getDocumentResponse(),
                pathParameters(
                        parameterWithName("surveyId").description("설문 아이디")
                ),
                responseFields(
                        getSurveyExtractor(),
                        fieldWithPath("surveyTitle").type(STRING).description("설문 제목"),
                        fieldWithPath("questions").type(ARRAY).description("설문 질문 리스트"),
                        fieldWithPath("surveyId").type(NUMBER).description("설문 ID")
                ))
        );
    }

    private void createFindRangeBarQuestionSnippet(ResultActions perform) throws Exception {
        perform.andDo(document("find_range_bar_question",
                getDocumentRequest(),
                getDocumentResponse(),
                pathParameters(
                        parameterWithName("surveyId").description("설문 아이디")
                ),
                responseFields(
                        getRangeQuestionExtractor(),
                        fieldWithPath("type").type(STRING).description("질문 타입"),
                        fieldWithPath("title").type(STRING).description("질문 제목"),
                        fieldWithPath("explanation").type(STRING).description("질문 설명"),
                        fieldWithPath("questionNumber").type(STRING).description("질문 번호"),
                        fieldWithPath("min").type(NUMBER).description("Ragne 답변 최솟값"),
                        fieldWithPath("max").type(NUMBER).description("Range 답변 최댓값"),
                        fieldWithPath("finalQuestion").type(BOOLEAN).description("마지막 질문인가"),
                        fieldWithPath("nextQuestionNumber").type(STRING).description("다음 질문 번호"),
                        fieldWithPath("questionId").type(NUMBER).description("질문의 id(pk)")
                ))
        );
    }

    private void createFindEssayQuestionSnippet(ResultActions perform) throws Exception {
        perform.andDo(document("find_essay_question",
                getDocumentRequest(),
                getDocumentResponse(),
                pathParameters(
                        parameterWithName("surveyId").description("설문 아이디")
                ),
                responseFields(
                        getEssayQuestionExtractor(),
                        fieldWithPath("type").type(STRING).description("질문 타입"),
                        fieldWithPath("title").type(STRING).description("질문 제목"),
                        fieldWithPath("explanation").type(STRING).description("질문 설명"),
                        fieldWithPath("questionNumber").type(STRING).description("질문 번호"),
                        fieldWithPath("finalQuestion").type(BOOLEAN).description("마지막 질문인가"),
                        fieldWithPath("nextQuestionNumber").type(STRING).description("다음 질문 번호"),
                        fieldWithPath("questionId").type(NUMBER).description("질문의 id(pk)")
                ))
        );
    }


    private void createFindMultipleChoiceQuestionSnippet(ResultActions perform) throws Exception {
        perform.andDo(document("find_multiple_choice_question",
                getDocumentRequest(),
                getDocumentResponse(),
                pathParameters(
                        parameterWithName("surveyId").description("설문 아이디")
                ),
                responseFields(
                        getMultipleChoiceQuestionExtractor(),
                        fieldWithPath("type").type(STRING).description("질문 타입"),
                        fieldWithPath("title").type(STRING).description("질문 제목"),
                        fieldWithPath("explanation").type(STRING).description("질문 설명"),
                        fieldWithPath("questionNumber").type(STRING).description("질문 번호"),
                        fieldWithPath("finalQuestion").type(BOOLEAN).description("마지막 질문인가"),
                        fieldWithPath("numberOfAnswerChoices").type(NUMBER).description("객관식에서 선택할 수 있는 질문의 개수"),
                        fieldWithPath("answers").type(ARRAY).description("객관식 답변 배열"),
                        fieldWithPath("answers[].answerId").type(NUMBER).description("객관식 답변 id"),
                        fieldWithPath("answers[].content").type(STRING).description("객관식 답변 내용"),
                        fieldWithPath("nextQuestionNumber").type(STRING).description("다음 질문 번호"),
                        fieldWithPath("logics").type(ARRAY).description("객관식에 설정할 다음 답변 이동 분기 로직"),
                        fieldWithPath("logics[].conditionOfQuestionAnswers").type(ARRAY).description("분기 로직에서 이동의 조건이 되는 객관식의 답변"),
                        fieldWithPath("logics[].conditionOfQuestionAnswers[].answerId").type(NUMBER).description("분기 로직에서 이동의 조건이 되는 객관식의 답변 id"),
                        fieldWithPath("logics[].conditionOfQuestionAnswers[].content").type(STRING).description("분기 로직에서 이동의 조건이 되는 객관식의 답변 내용"),
                        fieldWithPath("logics[].nextQuestionNumber").type(STRING).description("분기 로직에서 이동할 다음 질문 번호"),
                        fieldWithPath("questionId").type(NUMBER).description("질문의 id(pk)")
                ))
        );
    }
}
