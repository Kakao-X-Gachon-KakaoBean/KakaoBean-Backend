package com.kakaobean.controller;

import com.kakaobean.member.controller.MemberController;
import com.kakaobean.core.member.service.MemberService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = {
        MemberController.class
})
@AutoConfigureRestDocs
@ExtendWith(RestDocumentationExtension.class)
abstract class ControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    protected MemberService helloService;


}