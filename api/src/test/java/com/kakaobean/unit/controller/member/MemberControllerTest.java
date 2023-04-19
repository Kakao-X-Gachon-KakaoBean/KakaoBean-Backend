package com.kakaobean.unit.controller.member;

import com.kakaobean.member.dto.SendVerifiedEmailRequest;
import com.kakaobean.unit.controller.ControllerTest;
import com.kakaobean.unit.controller.factory.member.RegisterMemberRequestFactory;
import com.kakaobean.core.member.application.dto.request.RegisterMemberRequestDto;
import com.kakaobean.member.dto.RegisterMemberRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static com.kakaobean.docs.SpringRestDocsUtils.getDocumentRequest;
import static com.kakaobean.docs.SpringRestDocsUtils.getDocumentResponse;

import static org.mockito.BDDMockito.*;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.JsonFieldType.NUMBER;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MemberControllerTest extends ControllerTest {

    @Test
    @DisplayName("멤버 등록 API 명세서 테스트")
    void registerMemberTest() throws Exception {

        //given
        RegisterMemberRequest request = RegisterMemberRequestFactory.createRequest();
        String requestBody = objectMapper.writeValueAsString(request);
        given(memberService.registerMember(Mockito.any(RegisterMemberRequestDto.class)))
                        .willReturn(RegisterMemberRequestFactory.createResponseDto());

        //when
        ResultActions perform = mockMvc.perform(post("/members")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestBody)
        );

        //then
        perform.andDo(print());
        perform.andExpect(status().is2xxSuccessful());
        perform.andDo(document("register_member",
                getDocumentRequest(),
                getDocumentResponse(),
                requestFields(
                        fieldWithPath("name").type(STRING).description("이름"),
                        fieldWithPath("age").type(NUMBER).description("나이"),
                        fieldWithPath("gender").type(STRING).description("성별"),
                        fieldWithPath("email").type(STRING).description("이메일"),
                        fieldWithPath("emailAuthKey").type(STRING).description("이메일 인증 키"),
                        fieldWithPath("password").type(STRING).description("비밀번호"),
                        fieldWithPath("checkPassword").type(STRING).description("비밀번호 확인"),
                        fieldWithPath("birth").type(STRING).description("생일")
                ),
                responseFields(
                        fieldWithPath("memberId").type(NUMBER).description("등록한 회원 id")
                )
        ));
    }

    @Test
    @DisplayName("인증 메일 요청 API 명세서 테스트")
    void sendVerifiedEmail() throws Exception {

        //given
        SendVerifiedEmailRequest request = new SendVerifiedEmailRequest("gachon@gmail.com");
        String requestBody = objectMapper.writeValueAsString(request);

        //when
        ResultActions perform = mockMvc.perform(post("/emails")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestBody)
        );

        //then
        perform.andDo(print());
        perform.andExpect(status().is2xxSuccessful());
        perform.andDo(document("send_verified_email",
                getDocumentRequest(),
                getDocumentResponse(),
                requestFields(
                        fieldWithPath("email").type(STRING).description("인증 키를 받을 이메일")
                ),
                responseFields(
                        fieldWithPath("message").type(STRING).description("요청을 성공하셨습니다.")
                )
        ));
    }
}
