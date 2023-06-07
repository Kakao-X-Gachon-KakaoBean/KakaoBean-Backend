package com.kakaobean.unit.controller.chatbot;


import com.kakaobean.core.chatbot.application.dto.QuestionRequestDto;
import com.kakaobean.docs.SpringRestDocsUtils;
import com.kakaobean.unit.controller.ControllerTest;
import com.kakaobean.unit.controller.factory.chatbot.ChatGptResponseDtoFactory;
import com.kakaobean.unit.controller.security.WithMockUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.test.web.servlet.ResultActions;

import static com.kakaobean.docs.SpringRestDocsUtils.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ChatBotControllerTest extends ControllerTest {

    @WithMockUser
    @Test
    @DisplayName("챗봇 질문 api 테스트.")
    void senQuestionTest() throws Exception {

        QuestionRequestDto request = new QuestionRequestDto("코딩에 관한 설문 질문 추천해줘");
        String requestBody = this.objectMapper.writeValueAsString(request);
        given(chatGptService.askQuestion(Mockito.any(QuestionRequestDto.class)))
                .willReturn(ChatGptResponseDtoFactory.create());

        //when
        ResultActions perform = mockMvc.perform(post("/chat-gpt/question")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestBody)
        );

        //then
        perform.andDo(print());
        perform.andExpect(status().is2xxSuccessful());
//        perform.andDo(document("chatbot_send_question",
//                getDocumentRequest(),
//                getDocumentResponse(),
//                requestFields(
//                        fieldWithPath("question").type(STRING).description("chatgpt에게 물어볼 질문")
//                ),
//                responseFields(
//                        fieldWithPath("content").type(STRING).description("질문에 대한 chatgpt의 응답")
//                )
//        ));
    }
}
