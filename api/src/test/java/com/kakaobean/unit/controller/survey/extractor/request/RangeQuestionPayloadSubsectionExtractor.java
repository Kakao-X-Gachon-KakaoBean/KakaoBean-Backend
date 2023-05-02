package com.kakaobean.unit.controller.survey.extractor.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakaobean.survey.dto.request.RegisterSurveyRequest;
import com.kakaobean.survey.dto.request.question.RegisterQuestionRequest;
import com.kakaobean.survey.dto.request.question.RegisterRangeQuestionRequest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.PayloadSubsectionExtractor;

import java.io.IOException;

public class RangeQuestionPayloadSubsectionExtractor implements PayloadSubsectionExtractor {


    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] extractSubsection(byte[] payload, MediaType contentType) {
        try {
            //List<RegisterRangeQuestionRequest> result = new ArrayList<>();
            RegisterSurveyRequest request = objectMapper.readValue(payload, RegisterSurveyRequest.class);
            RegisterRangeQuestionRequest result = null;
            for (RegisterQuestionRequest question : request.getQuestions()) {
                if(question.getClass() == RegisterRangeQuestionRequest.class ){
                    RegisterRangeQuestionRequest findQuestion = (RegisterRangeQuestionRequest) question;
                    //result.add(findQuestion);
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
        return "questions";
    }

    @Override
    public PayloadSubsectionExtractor withSubsectionId(String subsectionId) {
        return null;
    }
}