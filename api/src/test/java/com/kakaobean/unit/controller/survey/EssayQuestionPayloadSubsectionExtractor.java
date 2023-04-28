package com.kakaobean.unit.controller.survey;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakaobean.survey.dto.request.RegisterSurveyRequest;
import com.kakaobean.survey.dto.request.question.RegisterEssayQuestionRequest;
import com.kakaobean.survey.dto.request.question.RegisterQuestionRequest;
import com.kakaobean.survey.dto.request.question.RegisterRangeQuestionRequest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.PayloadSubsectionExtractor;

import java.io.IOException;

public class EssayQuestionPayloadSubsectionExtractor implements PayloadSubsectionExtractor {


    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] extractSubsection(byte[] payload, MediaType contentType) {
        try {
            //List<RegisterRangeQuestionRequest> result = new ArrayList<>();
            RegisterSurveyRequest request = objectMapper.readValue(payload, RegisterSurveyRequest.class);
            RegisterEssayQuestionRequest result = null;
            for (RegisterQuestionRequest question : request.getQuestions()) {
                if(question.getClass() == RegisterEssayQuestionRequest.class ){
                    RegisterEssayQuestionRequest findQuestion = (RegisterEssayQuestionRequest) question;
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