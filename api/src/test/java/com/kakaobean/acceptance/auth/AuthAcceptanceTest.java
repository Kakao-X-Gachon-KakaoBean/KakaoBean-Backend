package com.kakaobean.acceptance.auth;

import com.kakaobean.acceptance.AcceptanceTest;
import com.kakaobean.acceptance.member.MemberAcceptanceTask;
import com.kakaobean.security.local.LocalLoginRequest;
import com.kakaobean.unit.controller.factory.member.RegisterMemberDtoFactory;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ExtractableResponse;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;


import static org.assertj.core.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.documentationConfiguration;


@ExtendWith({RestDocumentationExtension.class})
public class AuthAcceptanceTest extends AcceptanceTest {

    private RequestSpecification spec;

    @BeforeEach
    void setUp(RestDocumentationContextProvider restDocumentation) {
        this.spec = new RequestSpecBuilder()
                .addFilter(documentationConfiguration(restDocumentation))
                .build();
    }

    @Test
    void login(){

        //given
        MemberAcceptanceTask.registerMemberTask(RegisterMemberDtoFactory.createRequest());
        LocalLoginRequest loginRequest = new LocalLoginRequest("example@gmail.com", "1q2w3e4r!");

        //when
        ExtractableResponse response = AuthAcceptanceTask.login(loginRequest, spec);

        //then
        assertThat(response.statusCode()).isEqualTo(200);
    }
}
