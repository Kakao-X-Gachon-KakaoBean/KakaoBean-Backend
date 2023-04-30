package com.kakaobean.unit.controller.member;

import com.kakaobean.core.member.application.dto.response.FindEmailResponseDto;
import com.kakaobean.core.member.application.dto.response.FindMemberInfoResponseDto;
import com.kakaobean.core.member.domain.Gender;
import com.kakaobean.core.survey.application.dto.RegisterSurveyRequestDto;
import com.kakaobean.core.survey.application.dto.RegisterSurveyResponseDto;
import com.kakaobean.member.dto.FindEmailRequest;
import com.kakaobean.member.dto.SendVerifiedEmailRequest;
import com.kakaobean.survey.dto.request.RegisterSurveyRequest;
import com.kakaobean.unit.controller.ControllerTest;
import com.kakaobean.unit.controller.factory.member.RegisterMemberRequestFactory;
import com.kakaobean.core.member.application.dto.request.RegisterMemberRequestDto;
import com.kakaobean.member.dto.RegisterMemberRequest;
import com.kakaobean.unit.controller.factory.survey.RegisterSurveyRequestFactory;
import com.kakaobean.unit.controller.security.WithMockUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;

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


    @Test
    @DisplayName("이름, 생년월일을 통해 이메일을 찾는 API 명세서 테스트")
    void findEmail() throws Exception {

        //given
        FindEmailRequest req = new FindEmailRequest("bean", LocalDate.of(1999, 6, 27));
        given(memberService.findEmailByBirthAndName(Mockito.any(String.class), Mockito.any(LocalDate.class)))
                .willReturn(new FindEmailResponseDto("example@gmail.com"));
        String requestBody = objectMapper.writeValueAsString(req);

        //when
        ResultActions perform = mockMvc.perform(post("/members/find-email")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestBody)
        );

        //then
        perform.andDo(print());
        perform.andExpect(status().is2xxSuccessful());
        perform.andDo(document("find_email",
                getDocumentRequest(),
                getDocumentResponse(),
                requestFields(
                        fieldWithPath("name").type(STRING).description("이메일을 찾을 멤버 이름"),
                        fieldWithPath("birth").type(STRING).description("이메일을 찾을 멤버 생년월일")),
                responseFields(
                        fieldWithPath("email").type(STRING).description("찾은 이메일")
                )
        ));
    }

    @Test
    @WithMockUser
    @DisplayName("멤버 정보 API 명세서 테스트.")
    void findMemberInfo() throws Exception {

        //given
        given(memberService.findMemberInfoByMemberId(1L))
                .willReturn(new FindMemberInfoResponseDto("조연겸", 25, Gender.MALE, "whdusrua@naver.com", LocalDate.parse("1998-03-04")));

        //when
        ResultActions perform = mockMvc.perform(get("/members/find-member-info")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        //then
        perform.andDo(print());
        perform.andExpect(status().is2xxSuccessful());
        perform.andDo(document("find_member_info",
                getDocumentResponse(),
                responseFields(
                        fieldWithPath("name").type(STRING).description("찾은 멤버 이름"),
                        fieldWithPath("age").type(NUMBER).description("찾은 멤버 나이"),
                        fieldWithPath("gender").type(STRING).description("찾은 멤버 성별"),
                        fieldWithPath("email").type(STRING).description("찾은 멤버 이메일"),
                        fieldWithPath("birth").type(STRING).description("찾은 멤버 생일")
                )
        ));
    }
}
