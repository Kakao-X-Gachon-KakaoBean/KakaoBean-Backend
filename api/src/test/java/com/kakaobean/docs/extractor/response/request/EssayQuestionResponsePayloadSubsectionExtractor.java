package com.kakaobean.docs.extractor.response.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakaobean.response.dto.request.RegisterSurveyResponseRequest;
import com.kakaobean.response.dto.request.questionresponse.RegisterEssayQuestionResponseRequest;
import com.kakaobean.response.dto.request.questionresponse.RegisterQuestionResponseRequest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.PayloadSubsectionExtractor;

import java.io.IOException;

public class EssayQuestionResponsePayloadSubsectionExtractor implements PayloadSubsectionExtractor {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] extractSubsection(byte[] payload, MediaType contentType) {

        try {
            RegisterSurveyResponseRequest request = objectMapper.readValue(payload, RegisterSurveyResponseRequest.class);
            RegisterEssayQuestionResponseRequest result = null;
            for (RegisterQuestionResponseRequest question : request.getQuestions()) {
                if(question.getClass() == RegisterEssayQuestionResponseRequest.class ){
                    RegisterEssayQuestionResponseRequest findQuestion = (RegisterEssayQuestionResponseRequest) question;
                    result  = findQuestion;
                    break;
                }
            }
            //System.out.println(result);
            return objectMapper.writeValueAsBytes(result);
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
