package com.template.controller;

import com.template.controller.member.MemberController;
import com.template.core.service.member.MemberService;
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