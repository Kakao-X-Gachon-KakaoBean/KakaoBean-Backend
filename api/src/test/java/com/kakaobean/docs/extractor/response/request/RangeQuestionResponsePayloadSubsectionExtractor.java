package com.kakaobean.docs.extractor.response.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakaobean.response.dto.request.RegisterSurveyResponseRequest;
import com.kakaobean.response.dto.request.questionresponse.RegisterQuestionResponseRequest;
import com.kakaobean.response.dto.request.questionresponse.RegisterRangeQuestionResponseRequest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.PayloadSubsectionExtractor;

import java.io.IOException;

public class RangeQuestionResponsePayloadSubsectionExtractor implements PayloadSubsectionExtractor {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] extractSubsection(byte[] payload, MediaType contentType) {

        try {
            RegisterSurveyResponseRequest request = objectMapper.readValue(payload, RegisterSurveyResponseRequest.class);
            RegisterRangeQuestionResponseRequest result = null;
            for (RegisterQuestionResponseRequest question : request.getQuestions()) {
                if(question.getClass() == RegisterRangeQuestionResponseRequest.class ){
                    RegisterRangeQuestionResponseRequest findQuestion = (RegisterRangeQuestionResponseRequest) question;
                    result  = findQuestion;
                    break;
                }
            }
            return objectMapper.writeValueAsBytes(result); // 다시 Json으로
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public String getSubsectionId() {
        return null;
    }

    @Override
    public PayloadSubsectionExtractor withSubsectionId(String subsectionId) {
        return null;
    }
}
