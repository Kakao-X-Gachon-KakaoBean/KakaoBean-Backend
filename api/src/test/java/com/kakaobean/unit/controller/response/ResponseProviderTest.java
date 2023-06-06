package com.kakaobean.unit.controller.response;

import com.kakaobean.core.response.application.dto.response.FindResponsesDto;
import com.kakaobean.core.response.application.dto.response.SurveyResponseDto;
import com.kakaobean.core.survey.application.dto.response.FindSurveyResponseDto;
import com.kakaobean.docs.extractor.response.response.*;
import com.kakaobean.unit.controller.ControllerTest;
import com.kakaobean.unit.controller.factory.response.response.FindSurveyStatisticsResponseDtoFactory;
import com.kakaobean.unit.controller.factory.response.response.SurveyResponseDtoFactory;
import com.kakaobean.unit.controller.factory.survey.response.FindSurveyResponseFactory;
import com.kakaobean.unit.controller.security.WithMockUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static com.kakaobean.docs.SpringRestDocsUtils.getDocumentRequest;
import static com.kakaobean.docs.SpringRestDocsUtils.getDocumentResponse;

import static com.kakaobean.docs.extractor.survey.response.SurveyResponsePayloadSubsectionExtractorFactory.getSurveyExtractor;
import static org.mockito.BDDMockito.given;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ResponseProviderTest extends ControllerTest {

    @WithMockUser
    @Test
    @DisplayName("내가 생성한 설문에 대한 모든 응답 조회 API 명세서 테스트")
    public void findMySurveyResponses() throws Exception {

        // given
        FindSurveyResponseDto res1 = FindSurveyResponseFactory.create();
        List<SurveyResponseDto> res2 = SurveyResponseDtoFactory.createList();
        given(responseProvider.findResponses(Mockito.anyLong(), Mockito.anyLong()))
                .willReturn(new FindResponsesDto(res1, res2));

        // when
        ResultActions perform = mockMvc.perform(get("/responses/{surveyId}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        // then
        perform.andDo(print());
        perform.andExpect(status().is2xxSuccessful());


        createEssayQuestionResponseSnippet(perform);
        createMultipleQuestionResponseSnippet(perform);
        createRangeQuestionResponseSnippet(perform);
    }

    private void createEssayQuestionResponseSnippet(ResultActions perform) throws Exception {
        perform.andDo(document("find_essay_question_response",
                getDocumentRequest(),
                getDocumentResponse(),
                pathParameters(
                        parameterWithName("surveyId").description("설문 아이디")
                ),
                responseFields(
                        new EssayQuestionResponseDtoPayloadSubsectionExtractor(),
                        fieldWithPath("questionId").type(NUMBER).description("설문 질문의 id(pk)"),
                        fieldWithPath("answer").type(STRING).description("설문 질문 답변"),
                        fieldWithPath("type").type(STRING).description("설문 질문 타입"),
                        fieldWithPath("title").type(STRING).description("설문 질문제목")

                ))
        );
    }

    private void createMultipleQuestionResponseSnippet(ResultActions perform) throws Exception {
        perform.andDo(document("find_multiple_question_response",
                getDocumentRequest(),
                getDocumentResponse(),
                pathParameters(
                        parameterWithName("surveyId").description("설문 아이디")
                ),
                responseFields(
                        new MultipleChoiceQuestionResponseDtoPayloadSubsectionExtractor(),
                        fieldWithPath("questionId").type(NUMBER).description("설문 질문의 id(pk)"),
                        fieldWithPath("answers").type(ARRAY).description("설문 질문 객관식 답변"),
                        fieldWithPath("type").type(STRING).description("설문 질문 타입"),
                        fieldWithPath("title").type(STRING).description("설문 질문제목")

                ))
        );
    }

    private void createRangeQuestionResponseSnippet(ResultActions perform) throws Exception {
        perform.andDo(document("find_range_question_response",
                getDocumentRequest(),
                getDocumentResponse(),
                pathParameters(
                        parameterWithName("surveyId").description("설문 아이디")
                ),
                responseFields(
                        new RangeQuestionResponseDtoPayloadSubsectionExtractor(),
                        fieldWithPath("questionId").type(NUMBER).description("설문 질문의 id(pk)"),
                        fieldWithPath("answer").type(NUMBER).description("설문 질문 선형 배 답변"),
                        fieldWithPath("type").type(STRING).description("설문 질문 타입"),
                        fieldWithPath("title").type(STRING).description("설문 질문제목")
                ))
        );
    }

    @WithMockUser
    @Test
    @DisplayName("설문 결과 통계 조회 API 명세서 테스트")
    public void findSurveyStatisticsTest() throws Exception {

        // given
        given(responseProvider.findSurveyStatistics(Mockito.anyLong(), Mockito.anyLong()))
                .willReturn(FindSurveyStatisticsResponseDtoFactory.create());

        // when
        ResultActions perform = mockMvc.perform(get("/responses/survey-statistics/{surveyId}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        // then
        perform.andDo(print());
        perform.andExpect(status().is2xxSuccessful());

        createStatisticsSnippet(perform);
        createGenderRatioStatisticsSnippet(perform);
        createAgeStatisticsSnippet(perform);
        createEssayQuestionStatisticsSnippet(perform);
        createMultipleQuestionStatisticsSnippet(perform);
        createRangeQuestionStatisticsSnippet(perform);
    }

    private void createStatisticsSnippet(ResultActions perform) throws Exception {
        perform.andDo(document("find_Statistics_response",
                getDocumentRequest(),
                getDocumentResponse(),
                pathParameters(
                        parameterWithName("surveyId").description("설문 아이디")
                ),
                responseFields(
                        new StatisticsDtoPayloadSubsectionExtractor(),
                        fieldWithPath("surveyId").type(NUMBER).description("설문 ID"),
                        fieldWithPath("surveyTitle").type(STRING).description("설문 제목"),
                        fieldWithPath("surveyDate").type(STRING).description("설문 생성일자"),
                        fieldWithPath("numberOfResponse").type(NUMBER).description("설문 응답자 수"),
                        fieldWithPath("closeStatus").type(BOOLEAN).description("설문 마감 상태"),
                        fieldWithPath("surveyGenderPercent").type(ARRAY).description("성벌에 따른 통계 비율"),
                        fieldWithPath("surveyAgePercent").type(ARRAY).description("나이에 따른 통계 비율"),
                        fieldWithPath("questionsResult").type(ARRAY).description("질문에 대한 통계")
                ))
        );
    }

    private void createGenderRatioStatisticsSnippet(ResultActions perform) throws Exception {
        perform.andDo(document("find_GenderRatio_Statistics_response",
                getDocumentRequest(),
                getDocumentResponse(),
                pathParameters(
                        parameterWithName("surveyId").description("설문 아이디")
                ),
                responseFields(
                        new GenderRatioStatisticsDtoPayloadSubsectionExtractor(),
                        fieldWithPath("name").type(STRING).description("성별"),
                        fieldWithPath("value").type(NUMBER).description("해당 성별을 가진 사람이 설문에 참여한 퍼센트")
                ))
        );
    }

    private void createAgeStatisticsSnippet(ResultActions perform) throws Exception {
        perform.andDo(document("find_AgeRatio_Statistics_response",
                getDocumentRequest(),
                getDocumentResponse(),
                pathParameters(
                        parameterWithName("surveyId").description("설문 아이디")
                ),
                responseFields(
                        new AgeRatioStatisticsDtoPayloadSubsectionExtractor(),
                        fieldWithPath("name").type(STRING).description("나이 범위 ex) 10대"),
                        fieldWithPath("value").type(NUMBER).description("해당 나이대의 사람이 설문에 참여한 퍼센트")

                ))
        );
    }

    private void createEssayQuestionStatisticsSnippet(ResultActions perform) throws Exception {
        perform.andDo(document("find_essay_Statistics_response",
                getDocumentRequest(),
                getDocumentResponse(),
                pathParameters(
                        parameterWithName("surveyId").description("설문 아이디")
                ),
                responseFields(
                        new EssayQuestionStatisticsDtoPayloadSubsectionExtractor(),
                        fieldWithPath("type").type(STRING).description("설문 질문 타입"),
                        fieldWithPath("title").type(STRING).description("설문 질문 제목"),
                        fieldWithPath("explanation").type(STRING).description("설문 질문 설명"),
                        fieldWithPath("answers").type(ARRAY).description("모든 응답자의 Essay 답변 내용을 담은 배열")
                ))
        );
    }

    private void createMultipleQuestionStatisticsSnippet(ResultActions perform) throws Exception {
        perform.andDo(document("find_multiple_Statistics_response",
                getDocumentRequest(),
                getDocumentResponse(),
                pathParameters(
                        parameterWithName("surveyId").description("설문 아이디")
                ),
                responseFields(
                        new MultipleChoiceQuestionStatisticsDtoPayloadSubsectionExtractor(),
                        fieldWithPath("type").type(STRING).description("설문 질문 타입"),
                        fieldWithPath("title").type(STRING).description("설문 질문 제목"),
                        fieldWithPath("explanation").type(STRING).description("설문 질문 설명"),
                        fieldWithPath("answers").type(ARRAY).description("객관식 보기에 대한 배열"),
                        fieldWithPath("answers[].name").type(STRING).description("객관식 보기 내용"),
                        fieldWithPath("answers[].value").type(NUMBER).description("해당 보기를 선택한 응답 퍼센트")
                ))
        );
    }

    private void createRangeQuestionStatisticsSnippet(ResultActions perform) throws Exception {
        perform.andDo(document("find_range_Statistics_response",
                getDocumentRequest(),
                getDocumentResponse(),
                pathParameters(
                        parameterWithName("surveyId").description("설문 아이디")
                ),
                responseFields(
                        new RangeQuestionStatisticsDtoPayloadSubsectionExtractor(),
                        fieldWithPath("type").type(STRING).description("설문 질문 타입"),
                        fieldWithPath("title").type(STRING).description("설문 질문 제목"),
                        fieldWithPath("explanation").type(STRING).description("설문 질문 설명"),
                        fieldWithPath("min").type(NUMBER).description("설정된 Range의 최소값"),
                        fieldWithPath("max").type(NUMBER).description("설정된 Range의 최대값"),
                        fieldWithPath("answers").type(ARRAY).description("선형 배율 range 값에 대한 배열"),
                        fieldWithPath("answers[].name").type(STRING).description("Range 사이 중 하나의 값"),
                        fieldWithPath("answers[].value").type(NUMBER).description("해당 값을 선택한 응답 퍼센트")
                ))
        );
    }
}
