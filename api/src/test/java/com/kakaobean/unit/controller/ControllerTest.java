package com.kakaobean.unit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakaobean.config.AppProperties;
import com.kakaobean.config.SecurityConfig;
import com.kakaobean.config.WebMvcConfig;
import com.kakaobean.core.member.domain.MemberRepository;
import com.kakaobean.core.member.service.MemberService;
import com.kakaobean.member.MemberController;
import com.kakaobean.security.TokenProvider;

import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@Import({
        SecurityConfig.class,
        WebMvcConfig.class
})
@WebMvcTest(controllers = {
        MemberController.class
})
@AutoConfigureRestDocs
@MockBean(JpaMetamodelMappingContext.class)
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
public abstract class ControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected PasswordEncoder passwordEncoder;

    @MockBean
    protected TokenProvider tokenProvider;

    @MockBean
    protected MemberService memberService;

    @MockBean
    protected MemberRepository memberRepository;

    @MockBean
    protected AppProperties appProperties;

}