package com.kakaobean.unit.controller.response;

import com.kakaobean.core.response.application.dto.response.FindResponsesDto;
import com.kakaobean.core.response.application.dto.response.SurveyResponseDto;
import com.kakaobean.core.survey.application.dto.response.FindSurveyResponseDto;
import com.kakaobean.docs.extractor.response.response.EssayQuestionResponseDtoPayloadSubsectionExtractor;
import com.kakaobean.docs.extractor.response.response.MultipleChoiceQuestionResponseDtoPayloadSubsectionExtractor;
import com.kakaobean.docs.extractor.response.response.RangeQuestionResponseDtoPayloadSubsectionExtractor;
import com.kakaobean.unit.controller.ControllerTest;
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
    public void findMySurveyResponses() throws Exception{

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
                        fieldWithPath("type").type(STRING).description("설문 질문 타입")
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
                        fieldWithPath("type").type(STRING).description("설문 질문 타입")
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
                        fieldWithPath("type").type(STRING).description("설문 질문 타입")
                ))
        );
    }
}
