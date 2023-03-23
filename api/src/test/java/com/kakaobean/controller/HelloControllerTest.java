//package com.kakaobean.controller;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.ResultActions;
//
//import static com.kakaobean.docs.SpringRestDocsUtils.getDocumentRequest;
//import static com.kakaobean.docs.SpringRestDocsUtils.getDocumentResponse;
//import static org.mockito.Mockito.*;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
//import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
//import static org.springframework.restdocs.payload.JsonFieldType.STRING;
//import static org.springframework.restdocs.payload.PayloadDocumentation.*;
//import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
//import static org.springframework.restdocs.request.RequestDocumentation.*;
//import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//public class HelloControllerTest extends ControllerTest{
//
//    @Test
//    void helloTest() throws Exception {
//
//        when(helloService.registerHello()).thenReturn(3L);
//
//        ResultActions perform = mockMvc.perform(get("/?name=0")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//        );
//
//        perform.andDo(print());
//        perform.andExpect(status().is2xxSuccessful());
//        perform.andDo(document("hello-world",
//                getDocumentRequest(),
//                getDocumentResponse(),
//                requestParameters(
//                        parameterWithName("name").description("이름")
//                ),
//                responseFields(
//                        fieldWithPath("message").type(STRING).description("요청 성공 메시지")
//                )
//        ));
//
//    }
//}
